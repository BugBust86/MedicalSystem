package lds.com.medicalsystem.staff.labTech.vo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class LabItemSampleVO {
    private Integer itemId;
    private String itemName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
