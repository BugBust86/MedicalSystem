package lds.com.medicalsystem.staff.labTech.mapper;

import lds.com.medicalsystem.staff.labTech.entity.LabTech;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LabTechMapper {
    @Insert("insert into lab_tech(lab_no,lab_name,password,role)" +
            " values(#{doctorNo},#{doctorName},#{password},#{role})")
    void insert(LabTech labTech);
}
