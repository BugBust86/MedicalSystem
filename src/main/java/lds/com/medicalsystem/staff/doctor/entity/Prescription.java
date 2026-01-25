package lds.com.medicalsystem.staff.doctor.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

// 处方类
@Data
@TableName("prescription")
public class Prescription {
    // 主键
    private String prescriptionId;
    // 外键，关联Doctor，一个Doctor多个处方
    private String doctorNo;
    // 处方名
    private String prescriptionName;
    // 处方描述，对应数据库表的text结构
    private String prescriptionDesc;
    // 逻辑删除，1为有效，0为删除，默认为1
    private int is_valid;
    // 创建时间
    private String createTime;
    // 修改时间
    private String updateTime;
}
