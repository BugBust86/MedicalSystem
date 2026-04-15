package lds.com.medicalsystem.user.controller;

import lds.com.medicalsystem.common.VO.ResultVO;
import lds.com.medicalsystem.user.DTO.MedicalCardAddDTO;
import lds.com.medicalsystem.user.DTO.MedicalCardUpdateDTO;
import lds.com.medicalsystem.user.DTO.UserLoginDTO;
import lds.com.medicalsystem.user.DTO.UserRegisterDTO;
import lds.com.medicalsystem.user.UserTokenUtils;
import lds.com.medicalsystem.user.VO.CardInfoVO;
import lds.com.medicalsystem.user.VO.MedicalCardDetailVO;
import lds.com.medicalsystem.user.VO.PatientRecordDetailVO;
import lds.com.medicalsystem.user.VO.PatientRecordVO;
import lds.com.medicalsystem.user.VO.UserInfoVO;
import lds.com.medicalsystem.user.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin    // 允许所有前端域名跨域请求
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
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
        int userId = UserTokenUtils.getUserId();
        UserInfoVO userInfoVO = userService.showUserInfo(userId);
        return ResultVO.success(userInfoVO);
    }

    // 用户添加就诊卡,请求头传Token，请求体传json对象
    @PostMapping("/addMedicalCard")
    public ResultVO<Void> addCard(@Validated @RequestBody MedicalCardAddDTO dto) {
        int userId = UserTokenUtils.getUserId();
        dto.setUserId(userId);
        userService.addMedicalCard(dto);
        return ResultVO.success("添加成功");
    }
    // 用户查看就诊卡列表
    @GetMapping("/searchCardList")
    public ResultVO<List<CardInfoVO>> searchCardList(){
        int userId = UserTokenUtils.getUserId();
        List<CardInfoVO> cardList = userService.searchCardList(userId);
        return ResultVO.success(cardList);
    }
    // 用户就诊卡详情
    @GetMapping("/showMedicalCard")
    public ResultVO<MedicalCardDetailVO> showMedicalCard(int cardId){
        MedicalCardDetailVO mc = userService.showMedicalCard(cardId);
        return ResultVO.success(mc);
    }
    // 用户修改就诊卡
    @PutMapping("/updateMedicalCard")
    public ResultVO<Void> updateMedicalCard(@Validated @RequestBody MedicalCardUpdateDTO dto){
        userService.updateMedicalCard(dto);
        return ResultVO.success("修改成功");
    }

    // 用户查看就诊记录列表
    @GetMapping("/recordList")
    public ResultVO<List<PatientRecordVO>> recordList(){
        int userId = UserTokenUtils.getUserId();
        List<PatientRecordVO> recordList = userService.getRecordList(userId);
        return ResultVO.success(recordList);
    }

    // 用户查看就诊记录详情
    @GetMapping("/recordDetail")
    public ResultVO<PatientRecordDetailVO> recordDetail(int recordId){
        PatientRecordDetailVO detail = userService.getRecordDetail(recordId);
        return ResultVO.success(detail);
    }
}
