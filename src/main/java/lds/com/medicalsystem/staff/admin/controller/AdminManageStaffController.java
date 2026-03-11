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
    // 管理员查看所有医生，前端传role
    @GetMapping("/selectList/doctor")
    public ResultVO<List<DoctorListVO>> selectDoctorList(@RequestParam String role) {
        if (!role.equals("医生")) {
            throw new BusinessException("不是医生");
        }
        VerifyUtil.adminVerify();
        List<DoctorListVO> voList = adminStaffService.selectDoctorList();
        return ResultVO.success(voList);
    }
    // 管理员查看所有化验员，前端传role
    @GetMapping("/selectList/labTech")
    public ResultVO<List<LabTechListVO>> selectLabTechList(@RequestParam String role) {
        VerifyUtil.adminVerify();
        if(!role.equals("化验员")){
            throw new BusinessException("不是化验员");
        }
        List<LabTechListVO> voList = adminStaffService.selectLabTechList();
        return ResultVO.success(voList);
    }
    // 管理员点击编辑，可查看医生的头像（不可修改）
}
