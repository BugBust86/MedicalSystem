package lds.com.medicalsystem.staff.admin.service;

import lds.com.medicalsystem.common.utils.exception.BusinessException;
import lds.com.medicalsystem.staff.admin.VO.DeptSortVO;
import lds.com.medicalsystem.staff.admin.VO.DeptVO;
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
    public List<DeptSortVO> findAllDeptSort() {
        try {
            return adminDeptMapper.findAllDeptSort();
        } catch (Exception e) {
            throw new RuntimeException("返回所有科室列表失败", e);
        }
    }

    @Override
    public List<DeptVO> findAllDeptNameBySortName(String deptSortName) {
        System.out.println("[DEBUG] 准备查询分类：" + deptSortName);
        try {
            Integer id = adminDeptMapper.findDeptSortIdByName(deptSortName);
            if (id == null) {
                System.out.println("[ERROR] 分类不存在：" + deptSortName);
                throw new BusinessException("科室分类不存在：" + deptSortName);
            }
            System.out.println("[DEBUG] 查询到的分类 ID：" + id);
            List<DeptVO> result = adminDeptMapper.findAllDeptNameBySortName(id);
            System.out.println("[DEBUG] 查询结果数量：" + (result == null ? 0 : result.size()));
            return result;
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("返回该分类下所有科室失败",e);
        }
    }

    @Override
    public void insertDept(String deptName, int deptSortId) {
        try {
            adminDeptMapper.insertDept(deptName, deptSortId);
        } catch (Exception e) {
            throw new RuntimeException("新增科室失败",e);
        }
    }

    @Override
    public void deleteDept(int deptId) {
        try {
            adminDeptMapper.deleteDept(deptId);
        } catch (Exception e) {
            throw new RuntimeException("删除科室失败",e);
        }
    }

    @Override
    public List<DeptVO> findAllDept() {
        try {
            return adminDeptMapper.findAllDept();
        } catch (Exception e) {
            throw new RuntimeException("查询所有科室失败", e);
        }
    }
}
