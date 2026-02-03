package lds.com.medicalsystem.staff.doctor.VO;

import lds.com.medicalsystem.staff.doctor.DTO.PrescriptionAddDTO;
import lds.com.medicalsystem.staff.doctor.entity.PrescriptionDetail;
import lombok.Data;

import java.util.List;

@Data
public class PrescriptionDetailVO {
    private String prescriptionName; // 处方名
    private String prescriptionDesc; // 处方描述
    private String disease; // 使用疾病
    private String doctorId; // 医生ID（外键）

    // 处方药品详情列表
    private List<PrescriptionDetail> prescriptionDetails;

}
