package lds.com.medicalsystem.staff.labTech.controller;

import lds.com.medicalsystem.common.VO.ResultVO;
import lds.com.medicalsystem.staff.labTech.entity.CheckItem;
import lds.com.medicalsystem.staff.labTech.service.LabTechService;
import lds.com.medicalsystem.staff.labTech.vo.LabItemSampleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/labTech")
public class LabTechController {
    @Autowired
    private LabTechService labTechService;
    // 增加体检化验项目
    @PostMapping("/add")
    public ResultVO<Void> addCheckItem(@RequestBody CheckItem item) {
        try {
            labTechService.addCheckItem(item);
        } catch (Exception e) {
            throw new RuntimeException("新增项目失败",e);
        }
        return ResultVO.success("添加检查化验项目成功");
    }
    // 删除体检化验项目
    @DeleteMapping("/delete")
    public ResultVO<Void> deleteCheckItem(int itemId) {
        try {
            labTechService.deleteLabItem(itemId);
        } catch (Exception e) {
            throw new RuntimeException("删除失败",e);
        }
        return ResultVO.success("删除项目成功");
    }
    @PostMapping("/update")
    // 修改检查化验项目
    public ResultVO<Void> updateCheckItem(@RequestBody CheckItem item) {
        try {
            labTechService.updateLabItem(item);
        } catch (Exception e) {
            throw new RuntimeException("修改失败",e);
        }
        return ResultVO.success("修改项目成功");
    }
    @GetMapping("/queryAll")
    // 化验员查检查化验项目（区分工号，即化验员不可以查到其他化验员增删改的项目）
    public ResultVO<List<LabItemSampleVO>> queryCheckItemList(){
        List<LabItemSampleVO> labItemSampleVOS = labTechService.queryCheckItemList();
        return ResultVO.success(labItemSampleVOS);
    }
    @GetMapping("/queryOne")
    // 根据项目id查询单个项目的全部内容
    public ResultVO<CheckItem> queryOneCheckItem(int itemId) {
        CheckItem checkItem = labTechService.queryItemById(itemId);
        return ResultVO.success(checkItem);
    }
    @PatchMapping("/publish")
    // 化验员发布检查化验项目（用户可见）
    public ResultVO<Void> updateActiveStatus(int itemId) {
        try {
            labTechService.updateActiveStatus(itemId);
        } catch (Exception e) {
            throw new RuntimeException("发布失败",e);
        }
        return ResultVO.success("发布项目成功");
    }
}
