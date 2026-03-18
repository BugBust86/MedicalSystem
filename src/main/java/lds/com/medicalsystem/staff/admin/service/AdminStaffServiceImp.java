package lds.com.medicalsystem.staff.admin.service;

import lds.com.medicalsystem.common.utils.exception.BusinessException;
import lds.com.medicalsystem.staff.admin.DTO.AdminRegisterDoctorDTO;
import lds.com.medicalsystem.staff.admin.DTO.AdminRegisterLabDTO;
import lds.com.medicalsystem.staff.admin.VO.DoctorListVO;
import lds.com.medicalsystem.staff.admin.VO.LabTechListVO;
import lds.com.medicalsystem.staff.admin.mapper.AdminDeptMapper;
import lds.com.medicalsystem.staff.admin.mapper.AdminMapper;
import lds.com.medicalsystem.staff.doctor.entity.Doctor;
import lds.com.medicalsystem.staff.labTech.entity.LabTech;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AdminStaffServiceImp implements AdminStaffService {
    // 全局唯一的单例adminMapper（常量）
    private final AdminMapper adminMapper;
    private final AdminDeptMapper adminDeptMapper;
    // 将单例adminMapper注入该服务bean中（依赖注入）
    public AdminStaffServiceImp(AdminMapper adminMapper, AdminDeptMapper adminDeptMapper) {
        this.adminMapper = adminMapper;
        this.adminDeptMapper = adminDeptMapper;
    }

    //管理员注册医生
    @Override
    public void doctorRegisterByAdmin(AdminRegisterDoctorDTO dto) {
        // 先检查工号是否已存在
        Doctor d = adminMapper.selectDoctorByNo(dto.getDoctorNo());
        // 如果工号不为空，说明这个工号被占用了，报错已存在，若没问题，继续执行后续操作
        if(d != null){
            throw new BusinessException("该工号已存在");
        }
        try {
            dto.setDeptId(adminDeptMapper.findDeptIdByName(dto.getDeptName()));
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
        // 如果工号不为空，说明这个工号被占用了，报错已存在，若没问题，继续执行后续操作
        if(l != null){
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
    @Override
    public List<DoctorListVO> selectDoctorList() {
        try {
            return adminMapper.selectDoctorList();
        } catch (Exception e) {
            throw new RuntimeException("查询失败",e);
        }
    }
    @Override
    public List<LabTechListVO> selectLabTechList() {
        try {
            return adminMapper.selectLabTechList();
        } catch (Exception e) {
            throw new RuntimeException("查询失败",e);
        }
    }

    // 管理员根据工号删除医生
    @Override
    public void deleteDoctorByNo(String doctorNo) {
        Doctor doctor = adminMapper.selectDoctorByNo(doctorNo);
        if (doctor == null) {
            throw new BusinessException("医生工号不存在");
        }
        int result = adminMapper.deleteDoctorByNo(doctorNo);
        if (result == 0) {
            throw new BusinessException("删除医生失败");
        }
    }

    // 管理员根据工号删除化验员
    @Override
    public void deleteLabTechByNo(String labNo) {
        LabTech labTech = adminMapper.selectLabTechByNo(labNo);
        if (labTech == null) {
            throw new BusinessException("化验员工号不存在");
        }
        int result = adminMapper.deleteLabTechByNo(labNo);
        if (result == 0) {
            throw new BusinessException("删除化验员失败");
        }
    }

    // 管理员根据工号修改医生职称
    @Override
    public void updateDoctorTitle(String doctorNo, String title) {
        Doctor doctor = adminMapper.selectDoctorByNo(doctorNo);
        if (doctor == null) {
            throw new BusinessException("医生工号不存在");
        }
        int result = adminMapper.updateDoctorTitle(doctorNo, title);
        if (result == 0) {
            throw new BusinessException("修改职称失败");
        }
    }
}
