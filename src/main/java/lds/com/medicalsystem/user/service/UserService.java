package lds.com.medicalsystem.user.service;

import lds.com.medicalsystem.common.VO.ResultVO;
import lds.com.medicalsystem.user.VO.UserInfoVO;
import lds.com.medicalsystem.user.entity.MedicalCard;

public interface UserService {
    // 用户注册
    void register(String phone, String password, String userName);
    // 用户登录
    ResultVO<String> login(String name,String password);
    // 用户添加就诊卡
    void addMedicalCard(MedicalCard mc);

    UserInfoVO showUserInfo(String phone);

    MedicalCard showMedicalCard();
}
