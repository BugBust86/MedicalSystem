package lds.com.medicalsystem.user.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lds.com.medicalsystem.common.utils.ValidIdCard;
import lds.com.medicalsystem.user.entity.GenderType;
import lds.com.medicalsystem.user.entity.RelationType;
import lombok.Data;

// 新增就诊卡DTO
@Data
public class MedicalCardAddDTO {
    @NotBlank
    private String patientName;
    @NotBlank
    @ValidIdCard
    private String idNumber;
    private GenderType gender;
    private int age;
    @NotNull
    private RelationType relationship;
    @NotBlank
    private String contactPhone;
    // 关联User，前端无需传入，后端从token中解析即可
    private int userId;
}
