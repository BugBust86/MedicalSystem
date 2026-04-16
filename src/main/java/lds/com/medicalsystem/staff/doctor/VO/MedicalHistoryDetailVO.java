package lds.com.medicalsystem.staff.doctor.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lds.com.medicalsystem.user.entity.GenderType;
import lombok.Data;

import java.util.Date;

/**
 * 病历本详情VO
 */
@Data
public class MedicalHistoryDetailVO {
    // 预约日期
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date reserveDate;
    // 预约时间（上午/下午/晚上）
    private String reserveTime;
    // 患者姓名
    private String patientName;
    // 患者性别
    private GenderType gender;
    // 患者年龄
    private int age;
    // 联系电话
    private String contactPhone;
    // 科室
    private String deptName;
    // 医生姓名
    private String doctorName;
    // 过往病史
    private String medicalHistory;
    // 患者描述
    private String patientDescription;
    // 医生建议
    private String doctorAdvice;
}