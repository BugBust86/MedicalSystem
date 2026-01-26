package lds.com.medicalsystem.common;

import lds.com.medicalsystem.common.DTO.InnerLoginDTO;
import lds.com.medicalsystem.common.DTO.InnerRegisterDTO;
import lds.com.medicalsystem.common.DTO.StaffInformationDTO;
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
    public StaffServiceImp(DoctorMapper doctorMapper) {
        this.doctorMapper = doctorMapper;
    }
    @Autowired
    private LabTechMapper LabTechMapper;

    @Override
    public void staffRegister(InnerRegisterDTO dto) {
        // 若有工具类先对传入controller层的密码加密，这里需要先进行解码

        //查询工号在数据库是否存在，如果不存在抛出该员工不存在的异常
        switch (dto.getRole()) {
            case "医生":
                Doctor doctor = new Doctor();
                doctor.setDoctorNo(dto.getStaffId());
                doctor.setDoctorName(dto.getName());
                doctor.setPassword(dto.getPassword());

                // 插入Doctor表
                doctorMapper.insert(doctor);
                break;
            case "化验员":
                LabTech labTech = new LabTech();
                labTech.setLabNo(dto.getStaffId());
                labTech.setLabName(dto.getName());
                labTech.setPassword(dto.getPassword());

                // 插入LabTech表
                LabTechMapper.insert(labTech);
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
