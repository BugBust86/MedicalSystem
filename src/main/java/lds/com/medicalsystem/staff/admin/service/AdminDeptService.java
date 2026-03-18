package lds.com.medicalsystem.staff.admin.service;

import lds.com.medicalsystem.staff.admin.VO.DeptSortVO;
import lds.com.medicalsystem.staff.admin.VO.DeptVO;

import java.util.List;

public interface AdminDeptService {
    // 查看所有科室分类,返回deptSortId和deptSortName列表
    public List<DeptSortVO> findAllDeptSort();
    // 查看某个分类下的全部科室,传入分类名，内部通过分类名查id，再查等于这个id的所有科室
    public List<DeptVO> findAllDeptNameBySortName(String deptSortName);
    // 某分类下新增科室，传科室名，分类ID
    public void insertDept(String deptName, int deptSortId);
    // 某分类下删除科室，传科室ID
    public void deleteDept(int deptId);
    // 查询所有科室（不分分类）
    public List<DeptVO> findAllDept();
}
