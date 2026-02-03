package lds.com.medicalsystem.staff.doctor.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lds.com.medicalsystem.staff.doctor.entity.Prescription;
import lombok.Data;

import java.util.List;

@Data
public class PrescriptionUpdateDTO {
    private String doctorId;
    @NotNull(message = "处方ID不能为空")
    private int prescriptionId;

    private String prescriptionName;

    private String disease;

    private String prescriptionDesc;

    // 详情修改：支持新增/修改/删除（用id区分：有id=修改/删除，无id=新增）
    private List<DetailUpdateDTO> detailList;
    @Data
    public static class DetailUpdateDTO {
        // 详情ID（修改/删除时必填）
        private Integer detailId;

        // 操作类型：ADD-新增，UPDATE-修改，DELETE-删除
        @NotBlank(message = "详情操作类型不能为空")
        private String operateType;

        private String drugName;
        private String dosage;
        private String frequency;
        private String usage;
        private String drugRemark;
    }
}
