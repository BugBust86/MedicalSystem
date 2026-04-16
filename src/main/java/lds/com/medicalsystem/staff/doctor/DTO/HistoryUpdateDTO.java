package lds.com.medicalsystem.staff.doctor.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 更新病历本DTO
 */
@Data
public class HistoryUpdateDTO {
    @NotNull
    private Integer reservationId;
    // 过往病史
    private String medicalHistory;
    // 患者描述
    private String patientDescription;
    // 医生建议
    private String doctorAdvice;
}