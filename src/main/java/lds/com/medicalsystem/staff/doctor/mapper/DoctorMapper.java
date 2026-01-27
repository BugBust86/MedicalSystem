package lds.com.medicalsystem.staff.doctor.mapper;

import lds.com.medicalsystem.common.CommonMapper;
import lds.com.medicalsystem.staff.doctor.entity.Doctor;
import org.apache.ibatis.annotations.Insert;

// 医生相关业务的mapper层，Doctor区和公共区的service可以调用
public interface DoctorMapper extends CommonMapper {
    // 员工的密码可以为空，使得管理员新增员工调用sql的时候可以不输入密码，
    // 这是医生注册密码的sql语句
    @Insert("insert into doctor(doctor_no,doctor_name,password)" +
            " values(#{doctorNo},#{doctorName},#{password})")
    void insert(Doctor doctor);

}
