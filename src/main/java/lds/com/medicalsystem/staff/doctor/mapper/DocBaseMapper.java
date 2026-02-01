package lds.com.medicalsystem.staff.doctor.mapper;

public interface DocBaseMapper {
    // 根据医生表中的医生id联合科室表查询科室名
    String getDeptName(String DoctorNo);
}
