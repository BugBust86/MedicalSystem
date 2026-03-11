package lds.com.medicalsystem.staff.admin.mapper;


import lds.com.medicalsystem.common.MVC.CommonMapper;
import lds.com.medicalsystem.staff.admin.DTO.AdminRegisterDoctorDTO;
import lds.com.medicalsystem.staff.admin.DTO.AdminRegisterLabDTO;
import lds.com.medicalsystem.staff.admin.VO.DoctorListVO;
import lds.com.medicalsystem.staff.admin.VO.LabTechListVO;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface AdminMapper extends CommonMapper {

    // 插入医生信息，service层已做校验，故而这里不需要插入role，直接数据库默认”医生“
    @Insert("insert into doctor(doctor_no, doctor_name, doctor_pic, dept_id, title)" +
            " VALUES (#{doctorNo},#{doctorName},#{pic},#{deptId},#{title})")
    int insertDoctor(AdminRegisterDoctorDTO dto);   // 返回受影响的行数
    // 插入化验员信息，service层已做校验，故而这里不需要插入role，直接数据库默认”化验员“
    @Insert("insert into lab_tech(lab_no, lab_name, lab_pic) values(#{labNo},#{labName},#{pic})")
    int insertLabTech(AdminRegisterLabDTO dto);

    // 查询医生表全部医生的姓名、头像
    @Select("select d.doctor_no,d.doctor_name,dept.dept_name,d.title,d.phone " +
            "from doctor d inner join dept on d.dept_id = dept.dept_id")
    List<DoctorListVO> selectDoctorList();
    // 查询化验员表全部化验员的姓名、头像
    @Select("SELECT lab_no, lab_name, phone FROM lab_tech")
    List<LabTechListVO> selectLabTechList();

    // 传入工号，修改管理员账号的密码
    @Update("update admin set password=#{newPsw} where admin_no=#{adminNo}")
    void update(@Param("adminNo") String adminNo,@Param("newPsw") String newPsw);



}
