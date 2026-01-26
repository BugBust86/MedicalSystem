package lds.com.medicalsystem.common;

import lds.com.medicalsystem.common.DTO.InnerRegisterDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Validated    // @Validated会对参数、类等内部所有带有校验注解（如 @NotBlank、@NotNull、@Size）的字段进行校验
@RequestMapping("/api")     // 接口公共的最初请求路径
public class StaffController {
    private final StaffService staffService;
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping("/staff/register")
    public Result<Void> staffRegister(@RequestBody InnerRegisterDTO dto) {
        staffService.staffRegister(dto);
        return Result.success("注册成功");
    }
}
