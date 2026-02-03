package lds.com.medicalsystem.staff.doctor.entity;


import lombok.Data;

// 处方类
@Data

public class Prescription {
    // 主键
    private int prescriptionId;
    // 外键，关联Doctor，一个Doctor多个处方
    private String doctorNo;
    // 处方名
    private String prescriptionName;
    // 处方描述，对应数据库表的text结构
    private String prescriptionDesc;
    // 适用病例
    private String disease;
    // 创建时间
    private String createTime;
    // 修改时间
    private String updateTime;
}
