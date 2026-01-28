package lds.com.medicalsystem.common.MVC;

import lds.com.medicalsystem.common.DTO.InnerRegisterDTO;
import lds.com.medicalsystem.common.VO.ResultVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
// @Validated会对参数、类等内部所有带有校验注解（如 @NotBlank、@NotNull、@Size）的字段进行校验
@RequestMapping("/staff")     // 接口公共的最初请求路径
public class StaffController {
    private final StaffService staffService;
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    // 员工注册自己已有账号的密码
    @PostMapping("/registerBySelf")
    public ResultVO<Void> staffRegisterBySelf(@Validated @RequestBody InnerRegisterDTO dto) {
        staffService.staffRegisterBySelf(dto);
        return ResultVO.success("注册成功");
    }

}
