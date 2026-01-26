package lds.com.medicalsystem.user.entity;


import lombok.Data;

// 就诊卡

@Data
public class MedicalCard {
    // 主键，卡号
    private String cardId;
    private String patientName;
    // 身份证号
    private String idNumber;
    // 设为枚举类，1为男，0为女，数据库存int，前端收到1渲染成男，2渲染成女
    // 或者直接存男，女，表为枚举
    private GenderType gender;
    // 表对应类型为tinyint
    private int age;
    private RelationType relationship;
    private String contactPhone;
    // 1为有效，0为无效,默认为1,逻辑删除
    private int isActive;
    private String createTime;
    private String updateTime;
    // 外键，关联User
    private String userId;
}
