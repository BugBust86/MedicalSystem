package lds.com.medicalsystem.staff.labTech.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CheckItem {
    private int itemId;
    private String itemName;
    private String itemDesc;
    private String itemPlace;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime endTime;
    // 化验员发布后状态才为1，用户可查，默认为0
    private boolean isActive=false;
    // 外键，化验员工号
    private String labNo;
    // 设置可被预约的最大值
    private int reserveMax;

    // 查看单个具体信息用的
    private int reserved;
    private int reserveEmpty;

}
