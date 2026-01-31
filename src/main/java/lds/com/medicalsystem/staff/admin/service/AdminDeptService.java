package lds.com.medicalsystem.staff.admin.service;

import java.util.List;

public interface AdminDeptService {
    // 查看所有科室分类,返回字符串列表
    public List<String> findAllDeptSort();
    // 查看某个分类下的全部科室,传入分类名，内部通过分类名查id，再查等于这个id的所有科室
    public List<String> findAllDeptNameBySortName(String deptSortName);
    // 某分类下新增科室，传科室名，科室分类名
    public void insertDept(String deptName, String deptSortName);
    // 某分类下删除科室，传科室名
    public void deleteDept(String deptName);
}
