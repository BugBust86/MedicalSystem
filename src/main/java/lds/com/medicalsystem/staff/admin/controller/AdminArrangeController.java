package lds.com.medicalsystem.staff.admin.controller;

import lds.com.medicalsystem.common.VO.ResultVO;
import lds.com.medicalsystem.common.utils.exception.BusinessException;
import lds.com.medicalsystem.staff.admin.DTO.DocOtherInfoDTO;
import lds.com.medicalsystem.staff.admin.DTO.SearchTableDTO;
import lds.com.medicalsystem.staff.admin.DTO.WorkTableInsertDTO;
import lds.com.medicalsystem.staff.admin.DTO.WorkTableUpdateDTO;
import lds.com.medicalsystem.staff.admin.VO.PageResultVO;
import lds.com.medicalsystem.staff.VerifyUtil;
import lds.com.medicalsystem.staff.admin.service.AdminArrangeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
// 管理员增、删、改、查值班表
public class AdminArrangeController {
    private final AdminArrangeService adminArrangeService;
    public AdminArrangeController(AdminArrangeService adminArrangeService) {
        this.adminArrangeService = adminArrangeService;
    }

    // 设置动态 sql，增加科室的筛选条件，同时都具备分页功能，前端传入页码、pageSize、deptName 后显示值班信息
    @GetMapping("/workList/search")
    public ResultVO<PageResultVO> searchWorkList(
            @RequestParam int page,
            @RequestParam int pageSize,
            @RequestParam(required = false) String deptName,
            @RequestParam(required = false) String doctorName) {
        System.out.println("[DEBUG] Controller 收到请求：/admin/workList/search");
        System.out.println("[DEBUG] 参数：page=" + page + ", pageSize=" + pageSize + ", deptName=" + deptName + ", doctorName=" + doctorName);
        
        if(VerifyUtil.adminVerify()) {
            System.out.println("[DEBUG] 管理员验证通过，开始查询...");
            PageResultVO pageResultVO = adminArrangeService.searchWorkList(page, pageSize, deptName, doctorName);
            System.out.println("[DEBUG] 查询完成，返回结果");
            return ResultVO.success(pageResultVO);
        } else {
            System.out.println("[ERROR] 管理员验证失败");
            return ResultVO.error("非法角色");
        }
    }

    //点击列表项的编辑图标可查看单项详细信息（前端通过列表单项信息渲染，非后端工作）
    //可编辑修改单项内容，点击保存修改至数据库
    @PatchMapping("/workList/update")
    public ResultVO<Void> updateWorkInfo(@RequestBody WorkTableUpdateDTO dto){
        if(VerifyUtil.adminVerify()) {
            adminArrangeService.updateWorkInfo(dto);
            return ResultVO.success("修改值班信息成功");
        } return ResultVO.error("非法角色");
    }
    //管理员选择值班日期、时间段、值班医生工号(触发自动匹配医生姓名、所属科室）、最大预约人数，而后插入表
    @PostMapping("/workList/insert")
    public ResultVO<Void> insertWorkInfo(@RequestBody WorkTableInsertDTO dto){
        if(VerifyUtil.adminVerify()) {
            adminArrangeService.insertWorkInfo(dto);
            return ResultVO.success("插入值班信息成功");
        } return ResultVO.error("非法角色");
    }

    //可删除单项内容
    @DeleteMapping("/workList/delete")
    public ResultVO<Void> deleteWorkInfo(@RequestParam Integer id){
        if(VerifyUtil.adminVerify()) {
            adminArrangeService.deleteWorkInfo(id);
            return ResultVO.success("删除值班信息成功");
        } return ResultVO.error("非法角色");
    }
    //返回某个科室分类下的医生名列表
    @GetMapping("/docList/deptSort")
    public ResultVO<List<String>> DocNameListInDeptSort(@RequestParam String deptSortName){
        if(VerifyUtil.adminVerify()) {
            List<String> docNameList = adminArrangeService.docNameListInDeptSort(deptSortName);
            return ResultVO.success(docNameList);
        } else throw new BusinessException("非法角色");
    }
    //返回某个科室下的医生名列表
    @GetMapping("/docList/dept")
    public ResultVO<List<String>> DocNameListInDept(@RequestParam String deptName){
        if(VerifyUtil.adminVerify()) {
            List<String> docNameList = adminArrangeService.docNameListInDept(deptName);
            return ResultVO.success(docNameList);
        } else throw new BusinessException("非法角色");
    }
    //根据医生名查工号、科室（修改、添加时选择医生名称，工号、科室自动填充）
    @GetMapping("/findNoAndDept")
    public ResultVO<DocOtherInfoDTO> searchOtherByDocName(@RequestParam String docName) {
        if(VerifyUtil.adminVerify()) {
            DocOtherInfoDTO docOtherInfoDTO = adminArrangeService.searchOtherByDocName(docName);
            return ResultVO.success(docOtherInfoDTO);
        } else throw new BusinessException("非法角色");
    }
}
