package lds.com.medicalsystem.common.DTO;

import lombok.Data;

// 内部人员登录DTO
@Data
public class InnerLoginDTO {
    // 工号
    private String staffId;
    // 密码
    private String password;
    // 职责，doctor、labTech、admin
    private String role;
}
