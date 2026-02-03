package lds.com.medicalsystem.staff.labTech;

import jakarta.validation.constraints.Email;
import lombok.Data;

// 化验员修改个人中心时提交的内容
@Data
public class PersonalDTO {
    private String labNo;
    private String labTechName;
    private String newPhone;
    @Email(message = "请输入有效的邮箱地址")
    private String email;
}
