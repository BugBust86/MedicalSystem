package lds.com.medicalsystem.user.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lds.com.medicalsystem.staff.admin.entity.WorkTimeType;
import lombok.Data;

import java.util.Date;

@Data
public class UserReserveRequest {
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date reserveDate;
    private WorkTimeType reserveTime;
    private String doctorName;
}
