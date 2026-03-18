package lds.com.medicalsystem.staff.admin.mapper;

import lds.com.medicalsystem.staff.admin.VO.DeptSortVO;
import lds.com.medicalsystem.staff.admin.VO.DeptVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdminDeptMapper {
    // 通过科室分类名查科室分类 id
    @Select("select dept_sort_id from dept_sort where dept_sort_name = #{name};")
    Integer findDeptSortIdByName(String name);
    // 根据科室名查科室 id
    @Select("select dept_id from dept where dept_name = #{name}")
    Integer findDeptIdByName(String name);
    // 查看所有科室分类名
    @Select("SELECT dept_sort_id as deptSortId, dept_sort_name as deptSortName FROM dept_sort;")
    List<DeptSortVO> findAllDeptSort();
    // 查看某个分类下的全部科室
    @Select("select dept_id as deptId, dept_name as deptName from dept where dept_sort_id=#{deptSortId};")
    List<DeptVO> findAllDeptNameBySortName(int deptSortId);
    // 在科室表插入科室数据，主键自增
    @Insert("insert into dept(dept_name,dept_sort_id) values (#{deptName},#{deptSortId})")
    int insertDept(@Param("deptName") String deptName, @Param("deptSortId") int deptSortId);
    // 删除具体科室,根据deptId删除
    @Delete("delete from dept where dept_id=#{deptId}")
    int deleteDept(int deptId);
    // 查询所有科室（不分分类）
    @Select("select dept_id as deptId, dept_name as deptName from dept")
    List<DeptVO> findAllDept();
}
