package lds.com.medicalsystem.staff.doctor.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HistoryAddDTO {
    // 预约ID，病历本与预约记录一对一
    @NotNull
    private Integer reservationId;
    // 就诊卡ID
    @NotNull
    private Integer cardId;
    // 医生姓名
    private String doctorName;
    // 过往病史
    private String medical_history;
    // 病人描述
    @NotBlank
    private String patientDescription;
    // 医生建议
    private String doctorAdvice;

    // 新增后返回的病历ID
    private Integer historyId;
    // 就诊记录ID
    private Integer recordId;
}
