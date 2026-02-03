package lds.com.medicalsystem.staff.doctor.VO;

import lds.com.medicalsystem.user.entity.GenderType;
import lombok.Data;

@Data
public class PatientInfoVO {
    private String patientName;
    private String idNumber;
    private GenderType gender;
    private int age;
}
