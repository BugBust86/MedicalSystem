package lds.com.medicalsystem.user.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lds.com.medicalsystem.common.utils.ValidIdCard;
import lds.com.medicalsystem.user.entity.GenderType;
import lds.com.medicalsystem.user.entity.RelationType;
import lombok.Data;

// 修改就诊卡DTO
@Data
public class MedicalCardUpdateDTO {
    @NotNull
    private int cardId;
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
}
