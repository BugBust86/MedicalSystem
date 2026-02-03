package lds.com.medicalsystem.staff.doctor.controller;

import jakarta.validation.constraints.Email;
import lds.com.medicalsystem.common.VO.ResultVO;
import lds.com.medicalsystem.common.utils.config.ThreadLocalUtil;
import lds.com.medicalsystem.staff.admin.VO.WorkListVO;
import lds.com.medicalsystem.staff.VerifyUtil;
import lds.com.medicalsystem.staff.doctor.mapper.DoctorPersonalMapper;
import lds.com.medicalsystem.staff.doctor.DTO.InfoDTO;
import lds.com.medicalsystem.staff.doctor.DTO.PatientInfoDTO;
import lds.com.medicalsystem.staff.doctor.VO.PatientInfoVO;
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
    public ResultVO<List<WorkListVO>> searchDocWorkTable() {
        VerifyUtil.doctorVerify();
        Map<String,Object> map = ThreadLocalUtil.get();
        String doctorNo = (String)map.get("工号");
        List<WorkListVO> doctorWorkInfos=doctorPersonalService.searchDocWorkTable(doctorNo);
        return ResultVO.success(doctorWorkInfos);
    }
    // 传入医生工号、时间段、值班日期，返回当下全部患者对象列表：
    // 点击全部患者，查固定医生固定日期固定时间段的患者姓名+卡号对象列表
    @PostMapping("/patientList")
    public ResultVO<List<PatientInfoDTO>> searchAllPatient(@RequestBody InfoDTO dto){
        VerifyUtil.doctorVerify();
        List<PatientInfoDTO> patientList = doctorPersonalService.searchAllPatient(dto);
        return ResultVO.success(patientList);
    }

    // 点击具体的患者名字，查看病历本，显示就诊卡上的患者的详细信息（背后传入cardId，查全部患者时返回的)
    @GetMapping("/getPatientInfo")
    public ResultVO<PatientInfoVO> getPatientInfo(@RequestParam int cardId){
        VerifyUtil.doctorVerify();
        return ResultVO.success(doctorPersonalService.getPatientInfo(cardId));
    }

    // 修改个人中心的信息，如擅长、邮箱（工号、姓名、职称不可修改），动态修改（即可以提交部分）
    @PatchMapping("/updateInfo")
    public ResultVO<Void> updatePatientInfo(@RequestParam String specialty
            , @Email @RequestParam String email){
        VerifyUtil.doctorVerify();
        doctorPersonalService.update(specialty,email);
        return ResultVO.success("修改成功");
    }
    // 上传头像文件的接口
    @PostMapping("/upload")
    public ResultVO<String> upload(MultipartFile file) throws IOException {
        // 把文件内容存入到本地磁盘中
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        file.transferTo(new File("D:\\files\\"+fileName));
        dpm.updateURL("云服务器URL...");
        return ResultVO.success("云服务器URL...");
    }
}
