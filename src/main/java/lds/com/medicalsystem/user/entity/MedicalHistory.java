package lds.com.medicalsystem.user.entity;


import lombok.Data;

// 病历本（就诊记录的详情表）
@Data

public class MedicalHistory {
    private String historyId;
    // 病历本上医生填写的患者姓名，与就诊卡上的患者姓名一致
    private String name;
    private GenderType gender;
    private int age;
    // 过往病史
    private String medicalHistory;
    private String patientDescription;
    private String doctorAdvice;
    private String doctorName;
    private String createAt;
    private String recordId;
}
