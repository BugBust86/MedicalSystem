package lds.com.medicalsystem.user.mapper;


import lds.com.medicalsystem.user.DTO.UserReserveDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserReserveMapper {
    // 根据医生名查医生工号
    @Select("select doctor_no from doctor where doctor_name=#{doctorName};")
    String selectIdByName(String doctorName);
    // 查询reserve_empty的值
    @Select("select reserve_empty from work_table where work_date=#{dto.reserveDate} " +
            "and work_time=#{dto.reserveTime} and doctor_no=#{dto.doctorNo};")
    int queryReserveEmpty(@Param("dto") UserReserveDTO dto);
    // 更新操作，对work_table表的reserved+1
    @Update("update work_table set reserved = reserved+1 where work_date=#{dto.reserveDate} " +
            "and work_time=#{dto.reserveTime} and doctor_no=#{dto.doctorNo};")
    void updateReserved(@Param("dto") UserReserveDTO dto);
}
