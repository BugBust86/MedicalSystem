package lds.com.medicalsystem.staff.labTech.service;

import lds.com.medicalsystem.staff.labTech.entity.CheckItem;
import lds.com.medicalsystem.staff.labTech.vo.LabItemSampleVO;

import java.util.List;

public interface LabTechService {
    // 增加体检化验项目
    void addCheckItem(CheckItem item);
    //
    void deleteLabItem(int itemId);
    // 修改项目
    void updateLabItem(CheckItem item);
    // 化验员发布项目，更新项目状态
    void updateActiveStatus(int itemId);
    // 查询所有的检查化验项目，列表返回id+项目名+开始时间、结束时间
    List<LabItemSampleVO> queryCheckItemList();
    // 根据项目id查询项目的全部内容
    CheckItem queryItemById(int itemId);
}
