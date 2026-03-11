package lds.com.medicalsystem.user.VO;

import lds.com.medicalsystem.user.entity.GenderType;
import lombok.Data;

@Data
public class UserInfoVO {
    private String userName;
    // 注册时的手机号
    private String phone;
    // 性别
    private GenderType sex;
}
