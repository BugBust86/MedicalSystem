package lds.com.medicalsystem.staff.admin.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdminDeptMapper {
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
    int insertDept(@Param("deptName") String deptName, @Param("deptSortId") int deptSortId);
    // 删除具体科室,无需管分类
    @Delete("delete from dept where dept_name=#{deptName}")
    int deleteDept(String deptName);
    // 编辑修改某分类下的具体科室,把原来的科室名修改为新的。（可无）
}
