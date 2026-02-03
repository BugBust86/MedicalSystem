package lds.com.medicalsystem.staff.doctor.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

/**
 * 处方提交请求DTO,处方提交、处方修改都使用它
 * 前端只需传入除医生id外的所有东西
 */
@Data
public class PrescriptionAddDTO {
    // 处方主表信息
    @NotBlank(message = "处方名不能为空")
    private String prescriptionName; // 处方名
    @NotBlank(message = "医生工号不能为空")
    private String doctorId; // 医生ID（外键）
    @NotBlank(message = "使用疾病不能为空")
    private String disease;
    private String prescriptionDesc; // 处方描述


    // 处方详情列表（一对多）
    @NotNull(message = "处方药品详情不能为空")
    private List<PrescriptionDetailDTO> prescriptionDetails;

    // 处方详情DTO
    @Data
    public static class PrescriptionDetailDTO {
        @NotBlank(message = "药品名称不能为空")
        private String drugName; // 药品名称
        @NotBlank(message = "单次用量不能为空")
        private String dosage; // 单次用量
        private String frequency; // 每日次数
        private String usage; // 用药方式
        private String drugRemark; // 药品备注（可选）
    }
}