package lds.com.medicalsystem.staff.admin.DTO;

import lombok.Data;

@Data
public class AdminRegisterDoctorDTO {
    private String doctorNo;
    private String doctorName;
    private String phone;
    private String pic;
    // 区别化验员，医生还有部门、职称字段
    private String department;
    private String title;
    // 有备无患，以防万一（在service层加入校验，如果role不是医生就报错）
    private String role;
}
