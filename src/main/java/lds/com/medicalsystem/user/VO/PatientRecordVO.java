package lds.com.medicalsystem.user.VO;

import lombok.Data;

import java.util.Date;

// 患者端-就诊记录列表VO
@Data
public class PatientRecordVO {
    private Integer recordId;
    private Date appointmentDate;
    private String reserveTime;
    private String patientName;
    private String doctorName;
    private String deptName;
}
