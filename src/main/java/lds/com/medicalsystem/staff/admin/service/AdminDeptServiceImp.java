package lds.com.medicalsystem.staff.admin.service;

import lds.com.medicalsystem.staff.admin.mapper.AdminDeptMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminDeptServiceImp implements AdminDeptService {
    private final AdminDeptMapper adminDeptMapper;
    public AdminDeptServiceImp(AdminDeptMapper adminDeptMapper) {
        this.adminDeptMapper = adminDeptMapper;
    }
    @Override
    public List<String> findAllDeptSort() {
        try {
            return adminDeptMapper.findAllDeptSort();
        } catch (Exception e) {
            throw new RuntimeException("返回所有科室列表失败", e);
        }
    }

    @Override
    public List<String> findAllDeptNameBySortName(String deptSortName) {
        try {
            return adminDeptMapper.findAllDeptNameBySortName(adminDeptMapper.findDeptIdByName(deptSortName));
        } catch (Exception e) {
            throw new RuntimeException("返回该分类下所有科室失败",e);
        }
    }

    @Override
    public void insertDept(String deptName, String deptSortName) {
        try {
            int deptSortId = adminDeptMapper.findDeptIdByName(deptSortName);
            adminDeptMapper.insertDept(deptName, deptSortId);
        } catch (Exception e) {
            throw new RuntimeException("新增科室失败",e);
        }
    }

    @Override
    public void deleteDept(String deptName) {
        try {
            adminDeptMapper.deleteDept(deptName);
        } catch (Exception e) {
            throw new RuntimeException("删除科室失败",e);
        }
    }
}
