package lds.com.medicalsystem.common.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

// 内部人员注册DTO
@Data
public class InnerRegisterDTO {
    // 管理员账号只有一个，工号、姓名、密码等所有信息都是手动写进数据库的。
    // 其他员工的工号、姓名、手机号，还有医生的title（不可为空）都是由管理员web端调用接口添加进去的，密码无权添加
    @NotBlank       // 传参时结合@Validate
    private String staffId;
    @NotBlank
    private String name;
    @NotBlank
    private String password;
    // 新增传入手机号，保证安全性，仅有验证码确认后才能注册密码、修改密码
    @NotBlank
    private String phone;
    // controller层接收统一的DTO，service层再根据role分发处理
    @NotBlank
    private String role;
}
