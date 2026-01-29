package lds.com.medicalsystem.common.MVC;

import lds.com.medicalsystem.staff.admin.entity.Admin;
import lds.com.medicalsystem.staff.doctor.entity.Doctor;
import lds.com.medicalsystem.staff.labTech.entity.LabTech;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

// 抽离公共需要的sql查询方法，让医生业务、管理员业务、化验员业务的Mapper都继承它，实现方法复用
public interface CommonMapper {
    // 通过工号no查询医生信息，空返回null
    @Select("select * from doctor where doctor_no=#{doctorNo}")
    Doctor selectDoctorByNo(String doctorNo);

    // 通过工号no查询化验员信息，空返回null
    @Select("select * from lab_tech where lab_no= #{labNo}")
    LabTech selectLabTechByNo(String labNo);

    // 通过工号查管理员信息
    @Select("select * from admin where admin_no=#{adminNo}")
    Admin selectAdminByNo(String adminNo);
}
