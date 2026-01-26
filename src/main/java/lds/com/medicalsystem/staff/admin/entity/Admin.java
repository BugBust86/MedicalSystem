package lds.com.medicalsystem.staff.admin.entity;


import lombok.Data;

// 管理员类
@Data

public class Admin {
    private String adminNo;
    private String adminName;
    private String password;
    private String phone;
    private String email;
    private String createTime;
    private String updateTime;
    // 管理员头像
    private String adminPic;
}
