package lds.com.medicalsystem.staff.labTech.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

// 化验员
@Data
@TableName("lab_tech")
public class LabTech {
    private String labNo;
    private String labName;
    private String password;
    private String position;
    private String phone;
    private String email;
    private String createTime;
    private String updateTime;
    // 化验员头像
    private String labPic;
}
