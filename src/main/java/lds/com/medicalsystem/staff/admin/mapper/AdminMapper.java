package lds.com.medicalsystem.staff.admin.mapper;


import lds.com.medicalsystem.common.CommonMapper;
import lds.com.medicalsystem.staff.admin.DTO.AdminRegisterDoctorDTO;
import lds.com.medicalsystem.staff.admin.DTO.AdminRegisterLabDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;


public interface AdminMapper extends CommonMapper {

    // 插入医生信息，service层已做校验，故而这里不需要插入role，直接数据库默认”医生“
    @Insert("insert into doctor(doctor_no, doctor_name, doctor_pic, department, title)" +
            " VALUES (#{doctorNo},#{doctorName},#{pic},#{department},#{title})")
    int insertDoctor(AdminRegisterDoctorDTO dto);   // 返回受影响的行数


    // 插入化验员信息，service层已做校验，故而这里不需要插入role，直接数据库默认”化验员“
    @Insert("insert into lab_tech(lab_no, lab_name, lab_pic) values(#{labNo},#{labName},#{pic})")
    int insertLabTech(AdminRegisterLabDTO dto);

    // 传入工号，修改管理员账号的密码
    @Update("update admin set password=#{newPsw} where admin_no=#{adminNo}")
    void update(String adminNo, String newPsw);
}
