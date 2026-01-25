package lds.com.medicalsystem.common;

import lds.com.medicalsystem.common.DTO.InnerRegisterDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaffController {
    private final StaffService staffService;
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping("/staff/register")
    public String staffRegister(@Validated @RequestBody InnerRegisterDTO dto) {
        staffService.staffRegister(dto);
        return "注册成功";
    }
}
