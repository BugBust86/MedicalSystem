package lds.com.medicalsystem.staff.doctor.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lds.com.medicalsystem.staff.admin.entity.WorkTimeType;
import lombok.Data;

import java.util.Date;

@Data
public class DoctorWorkTableVO {
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT+8")  // 确保后端只返回时间，不包含时区信息的尾缀
    private Date workDate;
    private WorkTimeType workTime; // 时间段
    private String doctorNo; // 医生工号
    private String doctorName;  // 插入(修改）值班表时不用输入
    private String deptName;    // 插入值班表时不用输入
    private Integer reserveMax;
    private Integer reserved;   // 已预约人数，用于医生查看患者信息时输出的，管理员查看也可以输出
}
