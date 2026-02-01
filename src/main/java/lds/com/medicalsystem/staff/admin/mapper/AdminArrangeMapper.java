package lds.com.medicalsystem.staff.admin.mapper;

import lds.com.medicalsystem.staff.admin.DTO.DocOtherInfoDTO;
import lds.com.medicalsystem.staff.admin.DTO.SearchTableDTO;
import lds.com.medicalsystem.staff.admin.DTO.WorkTableInsertDTO;
import lds.com.medicalsystem.staff.admin.DTO.WorkTableUpdateDTO;
import lds.com.medicalsystem.staff.admin.VO.WorkListVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AdminArrangeMapper {
    // 动态条件+分页查询值班表（WorkListVO），若科室名不为空则查该科室名下的全部值班表，若科室名为空分类不为空则查分类下的全部
    List<WorkListVO> searchWorkListByCondition(SearchTableDTO dto);
    // 动态条件查询总条数
    int countWorkListByCondition(SearchTableDTO dto);
    // 联表查询，连接医生表和科室表，根据医生名查医生表的工号，科室表的科室名
    DocOtherInfoDTO searchNoDNameByDocName(String docName);
    // 连表查询，连接医生表和科室表，根据科室名查医生名列表
    List<String> docNameListInDept(String deptName);

    List<String> docNameListInDeptSort(String deptSortName);
    // 插入work_table
    @Insert("insert into work_table(work_date, work_time, doctor_no, reserve_max) " +
            "VALUES (#{dto.workDate},#{dto.workTime},#{dto.doctorNo},#{dto.reserveMax})")
    int insertWorkList(@Param("dto") WorkTableInsertDTO dto);
    @Update("update work_table set work_date=#{dto.workDate},work_time=#{dto.workTime}," +
            "doctor_no=#{dto.doctorNo},reserve_max=#{dto.reserveMax} where table_id=#{dto.id};")
    int updateWorkList(@Param("dto") WorkTableUpdateDTO dto);
    @Delete("delete from work_table where table_id = #{workTableId}")
    int deleteWorkList(Integer workTableId);
}
