package lds.com.medicalsystem.staff.doctor.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lds.com.medicalsystem.staff.admin.entity.WorkTimeType;
import lds.com.medicalsystem.user.entity.GenderType;
import lombok.Data;

import java.util.Date;

@Data
public class PatientReserveInfoVO {
    // 预约时间，这里不需要与work_table关联，因为用户查询预约信息本质是查 work_table，所以不会得到错误的信息
    // 当然它确实是 work_date 的子集，即这里出现的 date-time 组合 work_table 里面也一定会有
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date reserveDate;
    // 具体时间段
    private WorkTimeType reserveTime;
    // 患者姓名
    private String patientName;
    // 患者性别
    private GenderType gender;
    // 患者联系方式
    private String contactPhone;
    // 患者年龄
    private int age;
    // 预约的科室
    private String deptName;
    // 预约的医生名
    private String doctorName;
}
