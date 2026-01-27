package lds.com.medicalsystem.user.entity;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

// 就诊卡

@Data
public class MedicalCard {
    // 主键，卡号
    private String cardId;
    @NotBlank
    private String patientName;
    @NotBlank
    // 身份证号
    private String idNumber;
    // 设为枚举类，1为男，0为女，数据库存int，前端收到1渲染成男，2渲染成女
    // 或者直接存男，女，表为枚举
    private GenderType gender;
    // 表对应类型为tinyint
    private int age;
    @NotBlank
    private RelationType relationship;
    @NotBlank
    private String contactPhone;

    private String createTime;
    private String updateTime;
    // 外键，关联User
    private String userId;
}
