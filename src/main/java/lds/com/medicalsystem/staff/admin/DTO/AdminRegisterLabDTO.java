package lds.com.medicalsystem.staff.admin.DTO;

import lombok.Data;

@Data
public class AdminRegisterLabDTO {
    private String labNo;
    private String labName;
    private String phone;
    private String pic;
    // 有备无患，以防万一（在service层加入校验，如果role不是化验员就报错）
    private String role;
}
