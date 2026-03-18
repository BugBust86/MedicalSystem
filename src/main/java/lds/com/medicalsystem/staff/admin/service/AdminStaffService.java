package lds.com.medicalsystem.staff.admin.service;

import lds.com.medicalsystem.staff.admin.DTO.AdminRegisterDoctorDTO;
import lds.com.medicalsystem.staff.admin.DTO.AdminRegisterLabDTO;
import lds.com.medicalsystem.staff.admin.VO.DoctorListVO;
import lds.com.medicalsystem.staff.admin.VO.LabTechListVO;

import java.util.List;

public interface AdminStaffService {
    // 管理员注册医生
    void doctorRegisterByAdmin(AdminRegisterDoctorDTO dto);
    // 管理员注册化验员
    void labTechRegisterByAdmin(AdminRegisterLabDTO dto);
    // 管理员查询医生列表
    List<DoctorListVO> selectDoctorList();

    List<LabTechListVO> selectLabTechList();

    // 管理员根据工号删除医生
    void deleteDoctorByNo(String doctorNo);

    // 管理员根据工号删除化验员
    void deleteLabTechByNo(String labNo);

    // 管理员根据工号修改医生职称
    void updateDoctorTitle(String doctorNo, String title);
}
