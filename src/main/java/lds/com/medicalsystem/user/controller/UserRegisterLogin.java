package lds.com.medicalsystem.user.controller;

import jakarta.validation.constraints.Pattern;
import lds.com.medicalsystem.common.VO.ResultVO;
import lds.com.medicalsystem.user.entity.MedicalCard;
import lds.com.medicalsystem.user.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Validated
@CrossOrigin    // 允许所有前端域名跨域请求
public class UserRegisterLogin {
    private final UserService userService;
    public UserRegisterLogin(UserService userService) {
        this.userService = userService;
    }

    // 用户注册：输入姓名，手机号，验证码，密码，确认密码
    @PostMapping("/register")
    public ResultVO<Void> register(@Pattern(regexp = "^\\d{11}$") String phone,
                                   @Pattern(regexp = "^\\s{5,16}$") String psw) {
        // 姓名可重复；id是自增的，新注册的不会重复；手机号不可重复
        userService.register(phone, psw);
        return ResultVO.success("注册成功");
    }

    // 用户通过手机号登录,手机号+密码
    @PostMapping("/login")
    public ResultVO<String> login(@Pattern(regexp = "^\\d{11}$") String phone,
                                  @Pattern(regexp = "^\\d{5,16}$") String password) {
        return userService.login(phone, password);
    }

    // 用户添加就诊卡,请求头传Token，请求体传json对象
    @PostMapping("/addMedicalCard")
    public ResultVO<Void> addCard(@Validated @RequestBody MedicalCard mc) {
        // 不用做Token校验，已经设置添加了全局的拦截器，除/user/register和/user/login外的所有api都需要在请求头设置Token

        userService.addMedicalCard(mc);
        return ResultVO.success("添加成功");
    }
}
