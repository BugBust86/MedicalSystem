package lds.com.medicalsystem.staff.doctor.DTO;

import lombok.Data;
import java.util.List;

/**
 * 处方提交请求DTO
 */
@Data
public class PrescriptionSubmitDTO {
    // 处方主表信息
    private String prescriptionName; // 处方名
    private String prescriptionDesc; // 处方描述
    private Long doctorId; // 医生ID（外键）

    // 处方详情列表（一对多）
    private List<PrescriptionDetailDTO> prescriptionDetails;

    // 处方详情DTO
    @Data
    public static class PrescriptionDetailDTO {
        private String drugName; // 药品名称
        private String dosage; // 单次用量
        private String frequency; // 每日次数
        private String usage; // 用药方式
        private String drugRemark; // 药品备注（可选）
    }
}