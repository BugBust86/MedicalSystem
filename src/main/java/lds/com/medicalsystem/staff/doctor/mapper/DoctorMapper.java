package lds.com.medicalsystem.staff.doctor.mapper;

import lds.com.medicalsystem.staff.doctor.entity.Doctor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DoctorMapper {
    // 员工的密码可以为空，使得管理员新增员工调用sql的时候可以不输入密码，
    // 这是医生注册密码的sql语句
    @Insert("insert into doctor(doctor_no,doctor_name,password)" +
            " values(#{doctorNo},#{doctorName},#{password})")
    void insert(Doctor doctor);

}
