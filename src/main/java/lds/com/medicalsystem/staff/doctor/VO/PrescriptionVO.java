package lds.com.medicalsystem.staff.doctor.VO;

import lds.com.medicalsystem.staff.doctor.entity.PrescriptionDetail;
import lombok.Data;

import java.util.List;

@Data
public class PrescriptionVO {
    private Integer prescriptionId;
    private String prescriptionName;
    private String disease;
    private String prescriptionDesc;
    private List<PrescriptionDetail> prescriptionDetails;
    private String createTime;

}
