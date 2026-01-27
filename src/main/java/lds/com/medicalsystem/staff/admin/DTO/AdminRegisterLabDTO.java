package lds.com.medicalsystem.staff.admin.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AdminRegisterLabDTO {
    @NotBlank
    private String labNo;
    @NotBlank
    private String labName;
    @NotBlank
    private String phone;
    @NotBlank
    private String pic;
    // 有备无患，以防万一（在service层加入校验，如果role不是化验员就报错）
    @NotBlank
    private String role;
}
