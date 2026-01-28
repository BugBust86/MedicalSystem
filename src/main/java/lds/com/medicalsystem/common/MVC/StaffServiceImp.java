package lds.com.medicalsystem.common.MVC;

import lds.com.medicalsystem.common.DTO.InnerLoginDTO;
import lds.com.medicalsystem.common.DTO.InnerRegisterDTO;
import lds.com.medicalsystem.common.VO.StaffInformationVO;
import lds.com.medicalsystem.common.exception.BusinessException;
import lds.com.medicalsystem.staff.doctor.entity.Doctor;
import lds.com.medicalsystem.staff.doctor.mapper.DoctorMapper;
import lds.com.medicalsystem.staff.labTech.entity.LabTech;
import lds.com.medicalsystem.staff.labTech.mapper.LabTechMapper;
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

    // 医生、化验员注册\修改密码，输入工号、姓名、手机号、验证码（模拟），前端再传入role
    @Override
    public void staffRegisterBySelf(InnerRegisterDTO dto) {
        // 若有工具类先对传入controller层的密码加密，这里需要先进行解码


        switch (dto.getRole()) {
            case "医生":
                // 获取表中现有的该化验员信息，与前端传入的一一对比，对比姓名和绑定的手机号
                Doctor d = doctorMapper.selectDoctorByNo(dto.getStaffId());
                if(d==null){
                    throw new BusinessException("工号不存在，请联系管理员申请");
                }
                // 工号存在，校验工号对应的名字和前端传入的名字是否相等，不相等抛出BusinessException(“非法操作，请输入正确的工号或姓名”)
                if(!d.getDoctorName().equals(dto.getName())){
                    throw new BusinessException("非法操作，请输入正确的工号或姓名");
                }
                // 校验手机号是否绑定
                if(!d.getPhone().equals(dto.getPhone())){
                    throw new BusinessException("该手机号未绑定，请输入已绑定该工号的手机号");
                }

                // 校验完成，可将 前端传入dto的工号、密码参数传进sql语句，执行修改
                doctorMapper.doctorUpdate(d.getDoctorNo(),d.getPassword());
                break;
            case "化验员":
                // 获取表中现有的该化验员信息，与前端传入的一一对比，对比姓名和绑定的手机号
                LabTech lab = LabTechMapper.selectLabTechByNo(dto.getStaffId());
                if(lab==null){
                    throw new BusinessException("工号不存在，请联系管理员申请");
                }
                if(!lab.getLabName().equals(dto.getName())){
                    throw new BusinessException("非法操作，请输入正确的工号或姓名");
                }
                if(!lab.getPhone().equals(dto.getPhone())){
                    throw new BusinessException("该手机号未绑定，请输入已绑定该工号的手机号");
                }

                // 校验完成，可将 前端传入dto的工号、密码参数传进sql语句，执行修改
                LabTechMapper.labUpdate(dto.getStaffId(), dto.getPassword());
                break;
            case "管理员":
                throw new IllegalArgumentException("无注册管理员账号权限，请联系后端工程师找回账号");
            default:
                throw new IllegalArgumentException("不支持的角色: " + dto.getRole());
        }
    }
    // 员工登录
    @Override
    public String staffLogin(InnerLoginDTO innerLoginDTO) {
        return null;
    }
    // 查看个人中心
    @Override
    public StaffInformationVO staffInfo(InnerLoginDTO innerLoginDTO, String token) {
        return null;
    }
}
