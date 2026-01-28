package lds.com.medicalsystem.common;

import lds.com.medicalsystem.common.DTO.InnerLoginDTO;
import lds.com.medicalsystem.common.DTO.InnerRegisterDTO;
import lds.com.medicalsystem.common.VO.StaffInformationVO;

public interface StaffService {
    // 内部成员自己注册密码
    void staffRegisterBySelf(InnerRegisterDTO innerRegisterDTO);
    // 内部成员登录，返回一个Token
    String staffLogin(InnerLoginDTO innerLoginDTO);
    // 内部成员查看个人信息，传入登录的DTO和Token，Token用于控制权限，无权限任何服务接口都不能调用
    // 返回职工信息DTO
    StaffInformationVO staffInfo(InnerLoginDTO innerLoginDTO, String token);
}
