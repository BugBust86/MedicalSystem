package lds.com.medicalsystem.staff.labTech.mapper;

import lds.com.medicalsystem.staff.labTech.entity.LabTech;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface LabTechMapper {
    // 化验员注册密码的方法
    @Insert("insert into lab_tech(lab_no,lab_name,password) " +
            "values(#{labNo},#{labName},#{password})")
    void insert(LabTech labTech);
}
