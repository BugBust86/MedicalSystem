package lds.com.medicalsystem.common.MVC;

import lds.com.medicalsystem.common.DTO.InnerLoginDTO;
import lds.com.medicalsystem.common.DTO.InnerRegisterDTO;
import lds.com.medicalsystem.common.DTO.UpdatePswDTO;
import lds.com.medicalsystem.common.VO.ResultVO;
import lds.com.medicalsystem.common.VO.StaffInformationVO;
import lds.com.medicalsystem.common.utils.exception.BusinessException;
import lds.com.medicalsystem.common.utils.config.JWTUtil;
import lds.com.medicalsystem.staff.admin.entity.Admin;
import lds.com.medicalsystem.staff.admin.mapper.AdminMapper;
import lds.com.medicalsystem.staff.doctor.entity.Doctor;
import lds.com.medicalsystem.staff.doctor.mapper.DoctorMapper;
import lds.com.medicalsystem.staff.labTech.entity.LabTech;
import lds.com.medicalsystem.staff.labTech.mapper.LabTechMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StaffServiceImp implements StaffService {
    // 注入Mapper，代替@Autowired的写法
    private final DoctorMapper doctorMapper;
    private final LabTechMapper labTechMapper;
    private final AdminMapper adminMapper;
    public StaffServiceImp(DoctorMapper dM, LabTechMapper lM, AdminMapper aM) {
        this.doctorMapper = dM;
        this.labTechMapper = lM;
        this.adminMapper = aM;
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
                doctorMapper.doctorUpdate(dto.getStaffId(),dto.getPassword());
                break;
            case "化验员":
                // 获取表中现有的该化验员信息，与前端传入的一一对比，对比姓名和绑定的手机号
                LabTech lab = labTechMapper.selectLabTechByNo(dto.getStaffId());
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
                int i = labTechMapper.labUpdate(dto.getStaffId(), dto.getPassword());
                if(i==0){throw new BusinessException("修改失败");}
                break;
            case "管理员":
                throw new IllegalArgumentException("无注册管理员账号权限，请联系后端工程师找回账号");
        }
    }
    // 员工登录
    @Override
    public ResultVO<Map<String, String>> staffLogin(InnerLoginDTO dto) {
        switch (dto.getRole()) {
            case "医生":
                // 获取表中现有的该医生信息
                Doctor d = doctorMapper.selectDoctorByNo(dto.getStaffId());
                if(d==null){
                    throw new BusinessException("工号不存在，请联系管理员申请");
                }
                // 工号存在，检验密码是否存在，防止输入空
                if(d.getPassword()==null){
                    return ResultVO.error("未注册密码，请先注册"); //该处抛业务异常也可以，全局处理器会打包成ResultVO格式的json对象
                }
                if(!d.getPassword().equals(dto.getPassword())){     // 密码不相等
                    return ResultVO.error("工号或密码错误");
                }
                Map<String,Object> information1 = new HashMap<>();
                // 工号为token第二部分的有效信息，即该token解析得：“工号：xxx”
                information1.put("工号",dto.getStaffId());
                information1.put("role",dto.getRole());
                String token1 = JWTUtil.genToke(information1);
                Map<String,String> returnInformation1 = new HashMap<>();
                returnInformation1.put("token",token1);
                returnInformation1.put("name",d.getDoctorName());
                returnInformation1.put("role",d.getRole());
                return ResultVO.success("登录成功",returnInformation1);
            case "化验员":
                // 获取表中现有的该化验员信息，与前端传入的一一对比，对比姓名和绑定的手机号
                LabTech labTech = labTechMapper.selectLabTechByNo(dto.getStaffId());
                if(labTech==null){
                    throw new BusinessException("工号不存在，请联系管理员申请");
                }
                if(labTech.getPassword()==null){
                    return ResultVO.error("未注册密码，请先注册");
                }
                if(!labTech.getPassword().equals(dto.getPassword())){
                    return ResultVO.error("工号或密码错误");
                }
                Map<String,Object> information2 = new HashMap<>();
                information2.put("工号",dto.getStaffId());
                information2.put("role",dto.getRole());
                String token2 = JWTUtil.genToke(information2);
                Map<String,String> returnInformation2 = new HashMap<>();
                returnInformation2.put("token",token2);
                returnInformation2.put("name",labTech.getLabName());
                returnInformation2.put("role",labTech.getRole());
                return ResultVO.success("登录成功",returnInformation2);
            case "管理员":
                Admin admin = adminMapper.selectAdminByNo(dto.getStaffId());
                if(admin==null){
                    return ResultVO.error("工号输入错误");
                }
                if(!admin.getPassword().equals(dto.getPassword())){
                    return ResultVO.error("密码输入错误");
                }
                Map<String,Object> information3 = new HashMap<>();
                information3.put("工号",dto.getStaffId());
                information3.put("role",dto.getRole());
                String token3 = JWTUtil.genToke(information3);
                Map<String,String> returnInformation3 = new HashMap<>();
                returnInformation3.put("token",token3);
                returnInformation3.put("name",admin.getAdminName());
                returnInformation3.put("role",admin.getRole());
                return ResultVO.success("登录成功",returnInformation3);
        }
        return ResultVO.error("非法角色");
    }
    // 查看个人中心
    @Override
    public StaffInformationVO staffInfo(String staffId,String role) {
        StaffInformationVO VO = new StaffInformationVO();
        switch (role){
            case "医生":
                Doctor doctor = doctorMapper.selectDoctorByNo(staffId);
                VO.setPic(doctor.getDoctorPic());
                VO.setStaffId(doctor.getDoctorNo());
                VO.setName(doctor.getDoctorName());
                VO.setPhone(doctor.getPhone());
                VO.setEmail(doctor.getEmail());
                VO.setDeptName(doctorMapper.getDeptName(staffId));   //
                VO.setSpecialty(doctor.getSpecialty());     //
                VO.setTitle(doctor.getTitle());     //
                VO.setRole(doctor.getRole());
                return VO;
            case "管理员":
                Admin admin = adminMapper.selectAdminByNo(staffId);
                VO.setPic(admin.getAdminPic());
                VO.setStaffId(admin.getAdminNo());
                VO.setName(admin.getAdminName());
                VO.setPhone(admin.getPhone());
                VO.setEmail(admin.getEmail());
                VO.setRole(admin.getRole());
                return VO;
            case "化验员":
                LabTech labTech = labTechMapper.selectLabTechByNo(staffId);
                VO.setPic(labTech.getLabPic());
                VO.setStaffId(labTech.getLabNo());
                VO.setName(labTech.getLabName());
                VO.setPhone(labTech.getPhone());
                VO.setEmail(labTech.getEmail());
                VO.setRole(labTech.getRole());
                return VO;
        }
        throw new BusinessException("非法角色");
    }

    @Override
    public void staffUpdatePsw(UpdatePswDTO dto) {
        switch (dto.getRole()){
            case "医生":
                Doctor d = doctorMapper.selectDoctorByNo(dto.getStaffId());
                if( d.getPassword().equals(dto.getOldPsw()) ){
                    doctorMapper.doctorUpdate(dto.getStaffId(), dto.getNewPsw());
                } else {
                    System.out.println(d.getPassword());
                    System.out.println(dto.getOldPsw());
                    System.out.println(dto.getNewPsw());
                    throw new BusinessException("旧密码错误，请重新输入");
                }
                break;
            case "管理员":
                Admin admin = adminMapper.selectAdminByNo(dto.getStaffId());
                if( admin.getPassword().equals(dto.getOldPsw()) ){
                    adminMapper.update(dto.getStaffId(), dto.getNewPsw());
                } else {
                    throw new BusinessException("旧密码错误，请重新输入");
                }
                break;
            case "化验员":
                LabTech labTech = labTechMapper.selectLabTechByNo(dto.getStaffId());
                if( labTech.getPassword().equals(dto.getOldPsw()) ){
                    labTechMapper.labUpdate(dto.getStaffId(),dto.getNewPsw());
                } else {
                    throw new BusinessException("旧密码错误，请重新输入");
                }
                break;
            default:
                throw new BusinessException("非法角色，无法修改密码");
        }
    }
}