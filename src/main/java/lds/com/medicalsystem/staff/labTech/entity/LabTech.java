package lds.com.medicalsystem.staff.labTech.entity;


import lombok.Data;

// 化验员
@Data

public class LabTech {
    private String labNo;
    private String labName;
    private String password;
    // 职位
    private String role;
    private String phone;
    private String email;
    private String createTime;
    private String updateTime;
    // 化验员头像
    private String labPic;
}
