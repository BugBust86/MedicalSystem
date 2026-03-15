package lds.com.medicalsystem.staff.doctor.service;

import lds.com.medicalsystem.staff.doctor.VO.DoctorWorkTableVO;

import java.util.List;

public interface DoctorPersonalService {
    // 传入医生工号，返回值班日期、时间段、工号、最大预约人数、已预约人数
    List<DoctorWorkTableVO> searchDocWorkTable(String doctorNo);


    // 修改医生的邮箱、擅长信息、电话号码
    void update(String email,String specialty,String phone);
}
