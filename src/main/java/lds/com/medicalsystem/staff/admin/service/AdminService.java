package lds.com.medicalsystem.staff.admin.service;

import lds.com.medicalsystem.staff.admin.DTO.AdminRegisterDoctorDTO;
import lds.com.medicalsystem.staff.admin.DTO.AdminRegisterLabDTO;

public interface AdminService {
    // 管理员注册医生
    void doctorRegisterByAdmin(AdminRegisterDoctorDTO dto);
    // 管理员注册化验员
    void labTechRegisterByAdmin(AdminRegisterLabDTO dto);
}
