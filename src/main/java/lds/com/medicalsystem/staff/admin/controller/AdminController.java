package lds.com.medicalsystem.staff.admin.controller;

import lds.com.medicalsystem.common.Result;
import lds.com.medicalsystem.staff.admin.DTO.AdminRegisterDoctorDTO;
import lds.com.medicalsystem.staff.admin.DTO.AdminRegisterLabDTO;
import lds.com.medicalsystem.staff.admin.service.AdminService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 管理员相关业务：自己登录，注册员工(增删改查)，
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    public AdminController(AdminService ad) {
        this.adminService = ad;
    }

    // 管理员注册医生账号(不包含密码)
        @PostMapping("/registerByAdmin/doctor")
    public Result<Void> doctorRegisterByAdmin(@RequestBody AdminRegisterDoctorDTO dto) {
        // findById判断工号是否存在，不存在抛出BusinessException(“工号不存在，请联系管理员申请”)
        adminService.doctorRegisterByAdmin(dto);
        return Result.success("注册成功");
    }
    // 管理员注册医生账号(不包含密码)
    @PostMapping("/registerByAdmin/labTech")
    public Result<Void> labTechRegisterByAdmin(@RequestBody AdminRegisterLabDTO dto) {
        // findById判断工号是否存在，不存在抛出BusinessException(“工号不存在，请联系管理员申请”)
        adminService.labTechRegisterByAdmin(dto);
        return Result.success("注册成功");
    }
}
