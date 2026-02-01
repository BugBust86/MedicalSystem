package lds.com.medicalsystem.staff.admin.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

// 点击值班管理后返回列表的单项对象
@Data
public class WorkListVO {
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date workDate;
    private String workTime; // 时间段
    private String doctorNo; // 医生工号
    private String doctorName;  // 插入值班表时不用输入
    private String deptName;    // 插入值班表时不用输入
    private Integer reserveMax;
}
