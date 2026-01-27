package lds.com.medicalsystem.staff.doctor.mapper;

import lds.com.medicalsystem.common.CommonMapper;
import lds.com.medicalsystem.staff.doctor.entity.Doctor;
import org.apache.ibatis.annotations.Update;

// 医生相关业务的mapper层，Doctor区和公共区的service可以调用
public interface DoctorMapper extends CommonMapper {
    // 员工的密码可以为空，使得管理员新增员工调用sql的时候可以不输入密码，
    // 这是医生注册\修改密码的sql语句
    @Update("update doctor set password=#{newPsw} where doctor_no=#{doctorNo}")
    int doctorUpdate(String doctorNo,String newPsw);
}
