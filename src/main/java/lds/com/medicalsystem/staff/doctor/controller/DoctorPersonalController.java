package lds.com.medicalsystem.staff.doctor.controller;

import lds.com.medicalsystem.common.VO.ResultVO;
import lds.com.medicalsystem.common.utils.config.ThreadLocalUtil;
import lds.com.medicalsystem.staff.VerifyUtil;
import lds.com.medicalsystem.staff.doctor.DTO.DoctorUpdateDTO;
import lds.com.medicalsystem.staff.doctor.VO.DoctorWorkTableVO;
import lds.com.medicalsystem.staff.doctor.mapper.DoctorPersonalMapper;
import lds.com.medicalsystem.staff.doctor.service.DoctorPersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/doctor")
@Validated
public class DoctorPersonalController {
    private final DoctorPersonalService doctorPersonalService;
    public DoctorPersonalController(DoctorPersonalService doctorPersonalService) {
        this.doctorPersonalService = doctorPersonalService;
    }
    @Autowired
    private DoctorPersonalMapper dpm;

    // 根据工号查看值班表(三表联查，返回值班日期、时间段、工号、值班人员、科室、最大预约人数
    @GetMapping("/workTable")
    public ResultVO<List<DoctorWorkTableVO>> searchDocWorkTable() {
        VerifyUtil.doctorVerify();
        Map<String,Object> map = ThreadLocalUtil.get();
        String doctorNo = (String)map.get("工号");
        List<DoctorWorkTableVO> doctorWorkInfos=doctorPersonalService.searchDocWorkTable(doctorNo);
        return ResultVO.success(doctorWorkInfos);
    }

    // 修改个人中心的信息，如擅长、邮箱（工号、姓名、职称不可修改），动态修改（即可以提交部分）
    @PatchMapping("/updateInfo")
    public ResultVO<Void> updateDoctorInfo(@RequestBody DoctorUpdateDTO dto){
        VerifyUtil.doctorVerify();
        doctorPersonalService.update(dto.getEmail(), dto.getSpecialty(), dto.getPhone());
        return ResultVO.success("修改成功");
    }

    // 上传头像文件的接口
    @PostMapping("/upload")
    public ResultVO<String> upload(MultipartFile file) throws IOException {
        VerifyUtil.doctorVerify();
        Map<String,Object> map = ThreadLocalUtil.get();
        String doctorNo = (String) map.get("工号");
        // 获取文件后缀
        String originalFilename = file.getOriginalFilename();
        // 文件名为随机的 UUID+.后面的后缀
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));

        // 获取项目根目录下的 uploads 文件夹
        String uploadPath = System.getProperty("user.dir") + "/uploads/";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        // 保存文件
        file.transferTo(new File(uploadPath + fileName));
        // 生成可访问的 URL
        String imageUrl = "http://localhost:8080/images/" + fileName;
        // 更新数据库中的头像 URL
        dpm.updateURL(doctorNo, imageUrl);
        return ResultVO.success("上传头像成功",imageUrl);
    }
}
