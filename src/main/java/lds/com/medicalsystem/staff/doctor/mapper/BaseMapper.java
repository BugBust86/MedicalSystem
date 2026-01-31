package lds.com.medicalsystem.staff.doctor.mapper;

import org.apache.ibatis.annotations.Select;

public interface BaseMapper {
    // 根据医生表中的科室id查科室名
    String getDeptName(String DoctorNo);
}
