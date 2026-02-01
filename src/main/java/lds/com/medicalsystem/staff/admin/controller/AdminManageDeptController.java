package lds.com.medicalsystem.staff.admin.controller;

import lds.com.medicalsystem.common.VO.ResultVO;
import lds.com.medicalsystem.staff.admin.VerifyUtil;
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

    // 管理员查看所有的科室分类（有原始数据，分类暂时不新增）
    @GetMapping("/findAllDeptSort")
    public ResultVO<List<String>> findAllDeptSort(){
        VerifyUtil.adminVerify();
        List<String> allDeptSort = adminDeptService.findAllDeptSort();
        return ResultVO.success(allDeptSort);
    }
    // 管理员查看某个科室分类下的全部科室
    @GetMapping("/findAllDept")
    public ResultVO<List<String>> findAllDeptNameBySortName(@RequestParam String deptSortName){
        VerifyUtil.adminVerify();
        List<String> allDeptName = adminDeptService.findAllDeptNameBySortName(deptSortName);
        return ResultVO.success(allDeptName);
    }
    // 管理员在某个分类下新增具体科室，输入科室代码和科室名
    @PostMapping("/addDept")
    public ResultVO<Void> addDept(@RequestParam String deptName, @RequestParam String deptSortName){
        VerifyUtil.adminVerify();
        adminDeptService.insertDept(deptName, deptSortName);
        return ResultVO.success("返回该分类下全部科室成功");
    }
    // 管理员在某个分类下删除具体科室
    @DeleteMapping("/deleteDept")
    public ResultVO<Void> deleteDept(@RequestParam String deptName){
        VerifyUtil.adminVerify();
        adminDeptService.deleteDept(deptName);
        return ResultVO.success("删除成功");
    }

    // 管理员在某个分类下编辑修改具体科室
}
