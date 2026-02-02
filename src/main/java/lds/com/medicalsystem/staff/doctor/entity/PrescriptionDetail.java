package lds.com.medicalsystem.staff.doctor.entity;


import lombok.Data;

// 处方详情类
@Data

public class PrescriptionDetail {
    // 详情表主键
    private String detailId;
    // 外键，关联Prescription
    private String prescriptionId;
    // 药品名称
    private String drugName;
    // 单次用量
    private String dosage;
    // 每日次数
    private String frequency;
    // 用药方式，如口服、外用
    private String usage;
    // 备注
    private String drugRemark;
}
