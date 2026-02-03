package lds.com.medicalsystem.staff.doctor.DTO;

import lombok.Data;

@Data
public class PatientInfoDTO {
    private int cardId; // 就诊卡id
    private String patientName;
}
