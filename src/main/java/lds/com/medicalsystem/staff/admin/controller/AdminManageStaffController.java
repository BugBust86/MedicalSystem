package lds.com.medicalsystem.staff.admin.controller;

import lds.com.medicalsystem.common.VO.ResultVO;
import lds.com.medicalsystem.staff.admin.DTO.AdminRegisterDoctorDTO;
import lds.com.medicalsystem.staff.admin.DTO.AdminRegisterLabDTO;
import lds.com.medicalsystem.staff.admin.VO.DoctorListVO;
import lds.com.medicalsystem.staff.admin.VO.LabTechListVO;
import lds.com.medicalsystem.staff.admin.VerifyUtil;
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

    // 管理员注册医生账号(不包含密码,也不能有手机号)
    @PostMapping("/registerByAdmin/doctor")
    public ResultVO<Void> doctorRegisterByAdmin(@RequestBody AdminRegisterDoctorDTO dto) {
        VerifyUtil.verify();
        adminStaffService.doctorRegisterByAdmin(dto);
        return ResultVO.success("注册成功");
    }
    // 管理员注册医生账号(不包含密码)
    @PostMapping("/registerByAdmin/labTech")
    public ResultVO<Void> labTechRegisterByAdmin(@RequestBody AdminRegisterLabDTO dto) {
        VerifyUtil.verify();
        adminStaffService.labTechRegisterByAdmin(dto);
        return ResultVO.success("注册成功");
    }
    // 管理员查看所有医生\化验员，前端传role
    @GetMapping("/selectList/doctor")
    public ResultVO<List<DoctorListVO>> selectDoctorList() {
        VerifyUtil.verify();
        List<DoctorListVO> voList = adminStaffService.selectDoctorList();
        return ResultVO.success(voList);
    }
    // 管理员查看所有医生\化验员，前端传role
    @GetMapping("/selectList/labTech")
    public ResultVO<List<LabTechListVO>> selectLabTechList() {
        VerifyUtil.verify();
        List<LabTechListVO> voList = adminStaffService.selectLabTechList();
        return ResultVO.success(voList);
    }

}
