package lds.com.medicalsystem.common.DTO;

import lombok.Data;

// 内部人员注册DTO
@Data
public class InnerRegisterDTO {
    // 管理员账号只有一个，工号、姓名、密码等所有信息都是手动写进数据库的。
    // 其他员工的工号、姓名、手机号，还有医生的title（不可为空）都是由管理员web端调用接口添加进去的，密码无权添加
    private String staffId;
    private String name;
    private String password;
    // controller层接收统一的DTO，service层再根据role分发处理
    private String role;
}
