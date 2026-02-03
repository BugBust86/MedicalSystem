package lds.com.medicalsystem.staff.labTech.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LabItem {
    private Integer itemId;
    private String itemName;
    private String itemDesc;
    private String itemPlace;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
