package lds.com.medicalsystem.user.entity;


import lombok.Data;

// 就诊记录 表（订单表）

@Data
public class MedicalRecord {
    // 编号
    private String recordId;
    // 就诊时间
    private String appointmentIDate;
    // 就诊人姓名，与就诊卡上的一致
    private String patientName;
    // 关联用户表，一个用户对应多张卡，也对应多条记录
    private String userId;
    // 管理就诊卡，一个就诊卡对应多条记录
    private String cardId;
    // 就诊记录产生的时间
    private String createAt;
}
