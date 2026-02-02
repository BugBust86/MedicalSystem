package lds.com.medicalsystem.staff.doctor.pojo;

import lombok.Data;

@Data
public class PatientInfoDTO {
    private int cardId; // 就诊卡id
    private String patientName;
}
