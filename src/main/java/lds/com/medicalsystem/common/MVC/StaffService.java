package lds.com.medicalsystem.common.MVC;

import lds.com.medicalsystem.common.DTO.InnerLoginDTO;
import lds.com.medicalsystem.common.DTO.InnerRegisterDTO;
import lds.com.medicalsystem.common.DTO.UpdatePswDTO;
import lds.com.medicalsystem.common.VO.ResultVO;
import lds.com.medicalsystem.common.VO.StaffInformationVO;

import java.util.Map;

public interface StaffService {
    // 内部成员自己注册密码
    void staffRegisterBySelf(InnerRegisterDTO innerRegisterDTO);
    // 内部成员登录，返回Token和员工对应的姓名
    ResultVO<Map<String, String>> staffLogin(InnerLoginDTO innerLoginDTO);
    // 内部成员查看个人信息，传入登录的DTO和Token，Token用于控制权限，无权限任何服务接口都不能调用
    // 返回职工信息DTO
    StaffInformationVO staffInfo(String staffId,String role);

    // 员工修改密码
    void staffUpdatePsw(UpdatePswDTO dto);
}
