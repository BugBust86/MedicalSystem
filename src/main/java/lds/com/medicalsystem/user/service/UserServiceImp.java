package lds.com.medicalsystem.user.service;

import lds.com.medicalsystem.common.exception.BusinessException;
import lds.com.medicalsystem.user.entity.MedicalCard;
import lds.com.medicalsystem.user.entity.User;
import lds.com.medicalsystem.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{
    private final UserMapper userMapper;
    public UserServiceImp(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    @Override
    public void register(String phone, String password) {
        User u = userMapper.checkPhoneExists(phone);
        if(u!=null){
            throw new BusinessException("该手机号已注册");
        }
        try {
            int i = userMapper.userRegister(phone, password);
            if (i == 0){
                throw new BusinessException("注册失败,无数据插入");
            }
        } catch (Exception e) {
            throw new BusinessException("手机号为["+phone+"]的用户注册失败,底层执行异常", e);
        }
    }
    public void addMedicalCard(MedicalCard mc) {
        // 调用Mapper层添加就诊卡
        int num = userMapper.addMedicalCard(mc);
        if (num == 0){
            throw new BusinessException("添加就诊卡失败");
        }

    }
}
