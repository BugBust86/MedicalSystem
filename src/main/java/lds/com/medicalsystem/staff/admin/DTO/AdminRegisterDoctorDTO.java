package lds.com.medicalsystem.staff.admin.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AdminRegisterDoctorDTO {
    @NotBlank
    private String doctorNo;
    @NotBlank
    private String doctorName;
    @NotBlank
    private String phone;
    @NotBlank
    private String pic;
    // 区别化验员，医生还有部门、职称字段
    @NotBlank
    private String department;
    @NotBlank
    private String title;
    // 有备无患，以防万一（在service层加入校验，如果role不是医生就报错）
    @NotBlank
    private String role;
}
