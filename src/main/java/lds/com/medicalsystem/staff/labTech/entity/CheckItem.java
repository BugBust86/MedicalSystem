package lds.com.medicalsystem.staff.labTech.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CheckItem {
    private int itemId;
    private String itemName;
    private String itemDesc;
    private String itemPlace;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    // 化验员发布后状态才为1，用户可查，默认为0
    private boolean isActive=false;
    // 外键，化验员工号
    private String labNo;
    // 设置可被预约的最大值
    private int reserveMax;
}
