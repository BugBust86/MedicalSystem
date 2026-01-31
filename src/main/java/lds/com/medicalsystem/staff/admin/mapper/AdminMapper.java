package lds.com.medicalsystem.staff.admin.mapper;


import lds.com.medicalsystem.common.MVC.CommonMapper;
import lds.com.medicalsystem.staff.admin.DTO.AdminRegisterDoctorDTO;
import lds.com.medicalsystem.staff.admin.DTO.AdminRegisterLabDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;


public interface AdminMapper extends CommonMapper {

    // 插入医生信息，service层已做校验，故而这里不需要插入role，直接数据库默认”医生“
    @Insert("insert into doctor(doctor_no, doctor_name, doctor_pic, dept_id, title)" +
            " VALUES (#{doctorNo},#{doctorName},#{pic},#{deptId},#{title})")
    int insertDoctor(AdminRegisterDoctorDTO dto);   // 返回受影响的行数
    // 插入化验员信息，service层已做校验，故而这里不需要插入role，直接数据库默认”化验员“
    @Insert("insert into lab_tech(lab_no, lab_name, lab_pic) values(#{labNo},#{labName},#{pic})")
    int insertLabTech(AdminRegisterLabDTO dto);

    // 查询医生表全部医生的姓名、头像
    @Select("SELECT doctor_name AS doctorName, doctor_pic AS doctorPic FROM doctor")
    List<Map<String,Object>> selectDoctorList();
    // 查询化验员表全部化验员的姓名、头像
    @Select("SELECT lab_name AS labTechName, lab_pic AS labTechPic FROM lab_tech")
    List<Map<String, Object>> selectLabTechList();

    // 传入工号，修改管理员账号的密码
    @Update("update admin set password=#{newPsw} where admin_no=#{adminNo}")
    void update(@Param("adminNo") String adminNo,@Param("newPsw") String newPsw);

    // 根据科室名查科室id
    @Select("select dept_id from dept where dept_name = #{name}")
    int findDeptIdByName(String name);
    // 查看所有科室分类名
    @Select("SELECT dept_sort_name FROM dept_sort;")
    List<String> findAllDeptSort();
    // 查看某个分类下的全部科室名
    @Select("select dept_name from dept where dept_sort_id=#{deptSortId};")
    List<String> findAllDeptNameBySortName(int deptSortId);
    // 在科室表插入科室数据，主键自增
    @Insert("insert into dept(dept_name,dept_sort_id) values (#{deptName},#{deptSortId})")
    int insertDept(@Param("deptName") String deptName,@Param("deptSortId") int deptSortId);
    // 删除具体科室,无需管分类
    @Delete("delete from dept where dept_name=#{deptName}")
    int deleteDept(String deptName);
    // 编辑修改某分类下的具体科室,把原来的科室名修改为新的

}
