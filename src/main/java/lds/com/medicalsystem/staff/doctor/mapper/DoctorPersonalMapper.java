package lds.com.medicalsystem.staff.doctor.mapper;

import lds.com.medicalsystem.staff.doctor.VO.DoctorWorkTableVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DoctorPersonalMapper {
    // 传入医生工号，查询值班表（包含值班日期、时间段、工号、最大预约人数,已预约人数
    List<DoctorWorkTableVO> searchDoctorWorkTable(String doctorNO);

    // 一一对应，更新医生个人信息
    int update(@Param("doctorNo") String doctorNo, @Param("email") String email,
               @Param("specialty") String specialty,@Param("phone") String phone);

    // 更新医生表的头像url
    int updateURL(String doctorNo, String doctorPic);
}
