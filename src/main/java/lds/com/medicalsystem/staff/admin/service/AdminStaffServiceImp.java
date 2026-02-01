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
        List<Map<String, Object>> doctorMapList = adminMapper.selectDoctorList();
        // 转换为VO对象
        List<DoctorListVO> voList = new ArrayList<DoctorListVO>();
        for (Map<String, Object> map : doctorMapList) {
            DoctorListVO vo = new DoctorListVO();  //循环将map查询到的医生名和医生头像存入VO对象中，然后加进列表
            vo.setDoctorName((String)map.get("doctorName"));
            vo.setDoctorPic((String) map.get("doctorPic"));
            voList.add(vo);
        }
        return voList;
    }
    @Override
    public List<LabTechListVO> selectLabTechList() {
        List<Map<String, Object>> labTechMapList = adminMapper.selectLabTechList();
        // 转换为VO对象
        List<LabTechListVO> voList = new ArrayList<LabTechListVO>();
        for (Map<String, Object> map : labTechMapList) {
            LabTechListVO vo = new LabTechListVO();  //循环将map查询到的医生名和医生头像存入VO对象中，然后加进列表
            vo.setLabName((String)map.get("labTechName"));
            vo.setLabPic((String) map.get("labTechPic"));
            voList.add(vo);
        }
        return voList;
    }
}
