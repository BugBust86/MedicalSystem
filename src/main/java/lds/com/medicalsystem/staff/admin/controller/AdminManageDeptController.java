package lds.com.medicalsystem.staff.admin.controller;

import lds.com.medicalsystem.common.VO.ResultVO;
import lds.com.medicalsystem.common.utils.exception.BusinessException;
import lds.com.medicalsystem.staff.VerifyUtil;
import lds.com.medicalsystem.staff.admin.VO.DeptSortVO;
import lds.com.medicalsystem.staff.admin.VO.DeptVO;
import lds.com.medicalsystem.staff.admin.service.AdminDeptService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminManageDeptController {
    private final AdminDeptService adminDeptService;
    public AdminManageDeptController(AdminDeptService adminDeptService) {
        this.adminDeptService = adminDeptService;
    }

    // 管理员查看所有的科室分类（有原始数据，分类暂时不新增）,用户也可
    @GetMapping("/findAllDeptSort")
    public ResultVO<List<DeptSortVO>> findAllDeptSort(){
        if(VerifyUtil.adminVerify()||VerifyUtil.userVerify()) {
            List<DeptSortVO> allDeptSort = adminDeptService.findAllDeptSort();
            return ResultVO.success(allDeptSort);
        } else throw new BusinessException("非法操作");
    }
    // 管理员查看某个科室分类下的全部科室，用户也可
    @GetMapping("/findAllDept")
    public ResultVO<List<DeptVO>> findAllDeptNameBySortName(@RequestParam String deptSortName){
        if(VerifyUtil.adminVerify()||VerifyUtil.userVerify()) {
            List<DeptVO> allDeptName = adminDeptService.findAllDeptNameBySortName(deptSortName);
            return ResultVO.success(allDeptName);
        } else throw new BusinessException("非法操作");
    }
    // 管理员在某个分类下新增具体科室，输入科室名和分类ID
    @PostMapping("/addDept")
    public ResultVO<Void> addDept(@RequestParam String deptName, @RequestParam int deptSortId){
        VerifyUtil.adminVerify();
        adminDeptService.insertDept(deptName, deptSortId);
        return ResultVO.success("返回该分类下全部科室成功");
    }
    // 管理员在某个分类下删除具体科室
    @DeleteMapping("/deleteDept")
    public ResultVO<Void> deleteDept(@RequestParam int deptId){
        VerifyUtil.adminVerify();
        adminDeptService.deleteDept(deptId);
        return ResultVO.success("删除成功");
    }
    // 管理员查询所有科室（不分科室分类）
    @GetMapping("/findAllDeptNoSort")
    public ResultVO<List<DeptVO>> findAllDept(){
        if(VerifyUtil.adminVerify()||VerifyUtil.userVerify()) {
            List<DeptVO> allDept = adminDeptService.findAllDept();
            return ResultVO.success(allDept);
        } else throw new BusinessException("非法操作");
    }

}
