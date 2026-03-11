package lds.com.medicalsystem.common.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

// 内部人员登录DTO
@Data
public class InnerLoginDTO {
    @NotBlank
    private String staffId; // 工号
    // 密码
    @NotBlank
    private String password;
    // 职责，医生、化验员、管理员
    @NotBlank
    private String role;
}
