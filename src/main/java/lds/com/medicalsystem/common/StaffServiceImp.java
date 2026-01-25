package lds.com.medicalsystem.common;

import lds.com.medicalsystem.common.DTO.InnerLoginDTO;
import lds.com.medicalsystem.common.DTO.InnerRegisterDTO;
import lds.com.medicalsystem.common.DTO.StaffInformationDTO;
import lds.com.medicalsystem.staff.doctor.entity.Doctor;
import lds.com.medicalsystem.staff.doctor.mapper.DoctorMapper;
import org.springframework.stereotype.Service;


@Service
public class StaffServiceImp implements StaffService {
    // 注入Mapper，代替@Autowired的写法
    private final DoctorMapper doctorMapper;
    public StaffServiceImp(DoctorMapper doctorMapper) {
        this.doctorMapper = doctorMapper;
    }

    @Override
    public void staffRegister(InnerRegisterDTO dto) {
        // 若有工具类先对传入controller层的密码加密，这里需要先进行解码

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
                // 插入LabTech表
                break;
            case "管理员":
                // 插入Admin表
                break;
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
