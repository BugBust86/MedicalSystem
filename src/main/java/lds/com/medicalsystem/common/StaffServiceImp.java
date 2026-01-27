package lds.com.medicalsystem.common;

import lds.com.medicalsystem.common.DTO.InnerLoginDTO;
import lds.com.medicalsystem.common.DTO.InnerRegisterDTO;
import lds.com.medicalsystem.common.DTO.StaffInformationDTO;
import lds.com.medicalsystem.common.exception.BusinessException;
import lds.com.medicalsystem.staff.doctor.entity.Doctor;
import lds.com.medicalsystem.staff.doctor.mapper.DoctorMapper;
import lds.com.medicalsystem.staff.labTech.entity.LabTech;
import lds.com.medicalsystem.staff.labTech.mapper.LabTechMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StaffServiceImp implements StaffService {
    // 注入Mapper，代替@Autowired的写法
    private final DoctorMapper doctorMapper;
    private final LabTechMapper LabTechMapper;
    public StaffServiceImp(DoctorMapper doctorMapper, LabTechMapper LabTechMapper) {
        this.doctorMapper = doctorMapper;
        this.LabTechMapper = LabTechMapper;
    }

    @Override
    public void staffRegisterBySelf(InnerRegisterDTO dto) {
        // 若有工具类先对传入controller层的密码加密，这里需要先进行解码


        switch (dto.getRole()) {
            case "医生":
                // 判断工号是否存在，不存在抛出BusinessException(“工号不存在，请联系管理员申请”)
                Doctor d = doctorMapper.selectDoctorByNo(dto.getStaffId());
                if(d==null){
                    throw new BusinessException("工号不存在，请联系管理员申请");
                }
                // 工号存在，校验工号对应的名字和前端传入的名字是否相等，不相等抛出BusinessException(“非法操作，请输入正确的工号或姓名”)
                if(!d.getDoctorName().equals(dto.getName())){
                    throw new BusinessException("非法操作，请输入正确的工号或姓名");
                }
                // 工号存在，且姓名与工号对应，说明科室、职称、头像等其他信息也被管理员注册好了，只需注册密码
                d.setPassword(dto.getPassword());

                // d包含管理员原来注册的信息，又新增了密码，可以插入Doctor表
                doctorMapper.insert(d);
                break;
            case "化验员":
                LabTech lab = LabTechMapper.selectLabTechByNo(dto.getStaffId());
                if(lab==null){
                    throw new BusinessException("工号不存在，请联系管理员申请");
                }
                if(!lab.getLabName().equals(dto.getName())){
                    throw new BusinessException("非法操作，请输入正确的工号或姓名");
                }
                lab.setPassword(dto.getPassword());

                // 插入LabTech表
                LabTechMapper.insert(lab);
                break;
            case "管理员":
                throw new IllegalArgumentException("无注册管理员账号权限，请联系后端工程师找回账号");
            default:
                throw new IllegalArgumentException("不支持的角色: " + dto.getRole());
        }
    }
    @Override
    public String staffLogin(InnerLoginDTO innerLoginDTO) {
        return null;
    }
    @Override
    public StaffInformationDTO staffInfo(InnerLoginDTO innerLoginDTO, String token) {
        return null;
    }
}
