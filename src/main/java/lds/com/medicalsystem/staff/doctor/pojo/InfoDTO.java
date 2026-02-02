package lds.com.medicalsystem.staff.doctor.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lds.com.medicalsystem.staff.admin.entity.WorkTimeType;
import lombok.Data;

import java.util.Date;

@Data
public class InfoDTO {
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date workDate;
    private String doctorNo;
    private WorkTimeType workTime;
}
