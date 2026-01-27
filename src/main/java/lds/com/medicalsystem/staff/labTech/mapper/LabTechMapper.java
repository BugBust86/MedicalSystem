package lds.com.medicalsystem.staff.labTech.mapper;

import lds.com.medicalsystem.common.CommonMapper;
import lds.com.medicalsystem.staff.labTech.entity.LabTech;
import org.apache.ibatis.annotations.Insert;

// 化验员业务的mapper层，公共区（common包）和化验员区（labTech包）的service层可以调用
public interface LabTechMapper extends CommonMapper {
    // 化验员注册密码的方法
    @Insert("insert into lab_tech(lab_no,lab_name,password) " +
            "values(#{labNo},#{labName},#{password})")
    void insert(LabTech labTech);

}
