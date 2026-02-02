package lds.com.medicalsystem.staff.admin.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lds.com.medicalsystem.staff.admin.entity.WorkTimeType;
import lombok.Data;

import java.util.Date;

// 点击值班管理后返回列表的单项对象
@Data
public class WorkListVO {
    private Integer tableId;    // 查看信息时返回id（前端不显示），用于修改、删除单项信息时定位
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date workDate;
    private WorkTimeType workTime; // 时间段
    private String doctorNo; // 医生工号
    private String doctorName;  // 插入(修改）值班表时不用输入
    private String deptName;    // 插入值班表时不用输入
    private Integer reserveMax;
    private Integer reserved;   // 已预约人数，用于医生查看患者信息时输出的，管理员查看也可以输出
}
