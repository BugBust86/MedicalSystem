package lds.com.medicalsystem.user.controller;

import lds.com.medicalsystem.common.VO.ResultVO;
import lds.com.medicalsystem.common.utils.config.ThreadLocalUtil;
import lds.com.medicalsystem.user.DTO.UserLoginDTO;
import lds.com.medicalsystem.user.DTO.UserRegisterDTO;
import lds.com.medicalsystem.user.VO.UserInfoVO;
import lds.com.medicalsystem.user.entity.MedicalCard;
import lds.com.medicalsystem.user.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin    // 允许所有前端域名跨域请求
public class UserRegisterLoginController {
    private final UserService userService;
    public UserRegisterLoginController(UserService userService) {
        this.userService = userService;
    }

    // 用户注册：输入姓名，手机号，验证码，密码，确认密码
    @PostMapping("/register")
    public ResultVO<Void> register(@RequestBody @Validated UserRegisterDTO dto) {
        String phone = dto.getPhone();
        String psw = dto.getPsw();
        String userName = dto.getUserName();
        // 姓名可重复；id是自增的，新注册的不会重复；手机号不可重复
        userService.register(phone, psw, userName);
        return ResultVO.success("注册成功");
    }

    // 用户通过手机号登录,手机号+密码
    @PostMapping("/login")
    public ResultVO<String> login(@RequestBody @Validated UserLoginDTO dto) {
        String phone = dto.getPhone();
        String password = dto.getPsw();
        return userService.login(phone, password);
    }

    // 点击用户中心的头像显示用户信息
    @GetMapping("/Info")
    public ResultVO<UserInfoVO> userInfo(){
        // 请求头传入token，先在拦截器解析，成功后
        // 通过ThreadUtil的get获取phone（因为是通过手机号登录的）
        Map<String,Object> map = ThreadLocalUtil.get();
        String phone = (String)map.get("phone");
        UserInfoVO userInfoVO = userService.showUserInfo(phone);
        return ResultVO.success(userInfoVO);
    }

    // 用户添加就诊卡,请求头传Token，请求体传json对象
    @PostMapping("/addMedicalCard")
    public ResultVO<Void> addCard(@Validated @RequestBody MedicalCard mc) {
        // Token在先执行的全局拦截器中已经完成校验了，除/user/register和/user/login外的所有api都需要在请求头设置Token以便校验

        userService.addMedicalCard(mc);
        return ResultVO.success("添加成功");
    }
    // 用户查看就诊卡
    @GetMapping("/showMedicalCard")
    public ResultVO<MedicalCard> showMedicalCard(){

        MedicalCard mc = userService.showMedicalCard();
        return ResultVO.success(mc);
    }
}
