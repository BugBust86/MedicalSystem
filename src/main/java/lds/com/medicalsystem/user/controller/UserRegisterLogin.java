package lds.com.medicalsystem.user.controller;

import jakarta.validation.constraints.NotBlank;
import lds.com.medicalsystem.common.ResultVO;
import lds.com.medicalsystem.user.entity.MedicalCard;
import lds.com.medicalsystem.user.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Validated
public class UserRegisterLogin {
    private final UserService userService;
    public UserRegisterLogin(UserService userService) {
        this.userService = userService;
    }

    // 用户注册：输入姓名，手机号，验证码
    @PostMapping("/register")
    public ResultVO<Void> register(@NotBlank String phone, @NotBlank String psw) {
        // 姓名可重复；id是自增的，新注册的不会重复；手机号不可重复
        userService.register(phone, psw);
        return ResultVO.success("注册成功");
    }
    // 用户添加就诊卡
    @PostMapping("/addMedicalCard")
    public ResultVO<Void> addCard(MedicalCard mc) {
        userService.addMedicalCard(mc);
        return ResultVO.success("添加成功");
    }
}
