package lds.com.medicalsystem.staff.doctor.controller;

import lds.com.medicalsystem.staff.doctor.service.DoctorPersonalService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
public class DoctorPersonalController {
    private final DoctorPersonalService doctorPersonalService;
    public DoctorPersonalController(DoctorPersonalService doctorPersonalService) {
        this.doctorPersonalService = doctorPersonalService;
    }

    // 根据工号查看值班表

    // 修改个人中心的信息，如擅长、邮箱（工号、姓名、职称不可修改），动态修改（即可以提交部分）

    // 上传更新头像
}
