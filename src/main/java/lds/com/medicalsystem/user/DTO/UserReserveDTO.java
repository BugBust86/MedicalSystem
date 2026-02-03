package lds.com.medicalsystem.user.DTO;

import lds.com.medicalsystem.staff.admin.entity.WorkTimeType;
import lombok.Data;

import java.util.Date;

@Data
public class UserReserveDTO {
    // 预约日期，等同于work_table中的work_date
    private Date reserveDate;
    // 预约日期的时间段，枚举类，上午、下午、晚上
    private WorkTimeType reserveTime;
    // 预约的医生（工号）
    private String doctorNo;
}

