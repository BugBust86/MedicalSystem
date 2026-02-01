package lds.com.medicalsystem.staff.admin.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class WorkTableUpdateDTO {
    private int id;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date workDate;
    private String workTime;
    private String doctorNo;
    private String reserveMax;
}
