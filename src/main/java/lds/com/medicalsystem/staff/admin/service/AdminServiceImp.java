package lds.com.medicalsystem.staff.admin.service;

import lds.com.medicalsystem.common.exception.BusinessException;
import lds.com.medicalsystem.staff.admin.DTO.AdminRegisterDoctorDTO;
import lds.com.medicalsystem.staff.admin.DTO.AdminRegisterLabDTO;
import lds.com.medicalsystem.staff.admin.mapper.AdminMapper;
import lds.com.medicalsystem.staff.doctor.entity.Doctor;
import lds.com.medicalsystem.staff.labTech.entity.LabTech;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImp implements AdminService{
    // 全局唯一的单例adminMapper（常量）
    private final AdminMapper adminMapper;
    // 将单例adminMapper注入该服务bean中（依赖注入）
    public AdminServiceImp(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    //管理员注册医生
    @Override
    public void doctorRegisterByAdmin(AdminRegisterDoctorDTO dto) {
        // 先检查工号是否已存在
        Doctor d = adminMapper.selectDoctorByNo(dto.getDoctorNo());
        // 若这个医生的工号不存在，则可以执行注册操作，否则报错“该工号已存在”
        if(d == null){
            throw new BusinessException("该工号已存在");
        }
        try {
            int i = adminMapper.insertDoctor (dto);
            if (i == 0) {
                // 抛异常时，补充更具体的业务信息
                throw new BusinessException ("工号为 [" + dto.getDoctorNo () + "] 的医生注册失败，无数据插入");
            }
        } catch (Exception e) {
            // 如果是 SQL 等底层异常，包装成业务异常并传递原始异常
            throw new BusinessException ("工号为 [" + dto.getDoctorNo () + "] 的医生注册失败，底层执行异常", e);
        }
    }
    // 管理员注册化验员
    @Override
    public void labTechRegisterByAdmin(AdminRegisterLabDTO dto) {
        // 先检查工号是否已存在
        LabTech l = adminMapper.selectLabTechByNo(dto.getLabNo());
        // 若这个化验员工号不存在，则可以执行注册操作，否则报错“该工号已存在”
        if(l == null){
            throw new BusinessException("该工号已存在");
        }
        try {
            int i = adminMapper.insertLabTech(dto);
            if(i == 0){
                throw new BusinessException ("工号为 [" + dto.getLabNo () + "] 的化验员注册失败，无数据插入");
            }
        } catch (Exception e) {
            // 如果是 SQL 等底层异常，包装成业务异常并传递原始异常
            throw new BusinessException ("工号为 [" + dto.getLabNo () + "] 的化验员注册失败，底层执行异常", e);
        }
    }
}
