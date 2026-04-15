package lds.com.medicalsystem.user.VO;

import lombok.Data;

import java.util.Date;

// 患者端-就诊记录详情VO
@Data
public class PatientRecordDetailVO {
    private Integer recordId;
    private Date appointmentDate;
    private String reserveTime;
    private String patientName;
    private String doctorName;
    private String deptName;
    private String contactPhone;

    // 病历本信息
    private String medicalHistory;
    private String patientDescription;
    private String doctorAdvice;
    private String historyCreateAt;
}
