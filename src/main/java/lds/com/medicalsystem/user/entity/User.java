package lds.com.medicalsystem.user.entity;

import lombok.Data;

@Data

public class User {
    private String userId;
    private String userName;
    private String password;
    // 注册时的手机号
    private String phone;
    private int maxCards;
    private String createAt;
    private String updateAt;
    // 头像通过前段渲染，后端暂不支持上传头像（因为医疗系统不太用得上）
}
