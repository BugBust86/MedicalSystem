package lds.com.medicalsystem.staff.admin.controller;

import lds.com.medicalsystem.common.VO.ResultVO;
import lds.com.medicalsystem.common.utils.exception.BusinessException;
import lds.com.medicalsystem.staff.admin.DTO.AdminRegisterDoctorDTO;
import lds.com.medicalsystem.staff.admin.DTO.AdminRegisterLabDTO;
import lds.com.medicalsystem.staff.admin.VO.DoctorListVO;
import lds.com.medicalsystem.staff.admin.VO.LabTechListVO;
import lds.com.medicalsystem.staff.VerifyUtil;
import lds.com.medicalsystem.staff.admin.service.AdminStaffService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 管理员相关业务：自己登录，注册员工(增删改查)，
@RestController
@RequestMapping("/admin")
public class AdminManageStaffController {
    private final AdminStaffService adminStaffService;
    public AdminManageStaffController(AdminStaffService ad) {
        this.adminStaffService = ad;
    }

    // 管理员注册医生账号(不包含密码)
    @PostMapping("/registerByAdmin/doctor")
    public ResultVO<Void> doctorRegisterByAdmin(@RequestBody AdminRegisterDoctorDTO dto) {
        VerifyUtil.adminVerify();
        adminStaffService.doctorRegisterByAdmin(dto);
        return ResultVO.success("注册成功");
    }
    // 管理员注册医生账号(不包含密码)
    @PostMapping("/registerByAdmin/labTech")
    public ResultVO<Void> labTechRegisterByAdmin(@RequestBody AdminRegisterLabDTO dto) {
        VerifyUtil.adminVerify();
        adminStaffService.labTechRegisterByAdmin(dto);
        return ResultVO.success("注册成功");
    }
    // 管理员查看所有医生
    @GetMapping("/selectList/doctor")
    public ResultVO<List<DoctorListVO>> selectDoctorList() {
        VerifyUtil.adminVerify();
        List<DoctorListVO> voList = adminStaffService.selectDoctorList();
        return ResultVO.success(voList);
    }
    // 管理员查看所有化验员
    @GetMapping("/selectList/labTech")
    public ResultVO<List<LabTechListVO>> selectLabTechList() {
        VerifyUtil.adminVerify();
        List<LabTechListVO> voList = adminStaffService.selectLabTechList();
        return ResultVO.success(voList);
    }
    // 管理员根据工号删除医生
    @DeleteMapping("/delete/doctor/{doctorNo}")
    public ResultVO<Void> deleteDoctorByNo(@PathVariable String doctorNo) {
        VerifyUtil.adminVerify();
        adminStaffService.deleteDoctorByNo(doctorNo);
        return ResultVO.success("删除医生成功");
    }

    // 管理员根据工号删除化验员
    @DeleteMapping("/delete/labTech/{labNo}")
    public ResultVO<Void> deleteLabTechByNo(@PathVariable String labNo) {
        VerifyUtil.adminVerify();
        adminStaffService.deleteLabTechByNo(labNo);
        return ResultVO.success("删除化验员成功");
    }

    // 管理员根据工号，修改医生的职称
    @PutMapping("/update/doctorTitle")
    public ResultVO<Void> updateDoctorTitle(@RequestParam String doctorNo, @RequestParam String title) {
        VerifyUtil.adminVerify();
        adminStaffService.updateDoctorTitle(doctorNo, title);
        return ResultVO.success("修改医生职称成功");
    }
}
