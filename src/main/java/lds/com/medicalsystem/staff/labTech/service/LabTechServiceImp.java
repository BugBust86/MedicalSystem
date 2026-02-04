package lds.com.medicalsystem.staff.labTech.service;

import lds.com.medicalsystem.common.utils.config.ThreadLocalUtil;
import lds.com.medicalsystem.common.utils.exception.BusinessException;
import lds.com.medicalsystem.staff.labTech.entity.CheckItem;
import lds.com.medicalsystem.staff.labTech.mapper.LabTechMapper;
import lds.com.medicalsystem.staff.labTech.vo.LabItemSampleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LabTechServiceImp implements LabTechService{
    @Autowired
    private LabTechMapper labTechMapper;
    @Override
    public void addCheckItem(CheckItem item) {
        Map<String,Object> map = ThreadLocalUtil.get();
        String labNo = (String) map.get("工号");
        item.setLabNo(labNo);
        int i = labTechMapper.addCheckItem(item);   // 新增项目，返回受影响的行数
        if(i!=1){ throw new BusinessException("受影响的行数不为1，新增项目失败"); }
    }

    @Override
    public void deleteLabItem(int itemId) {
        int i = labTechMapper.deleteCheckItem(itemId);
        if(i!=1){ throw new BusinessException("受影响的行数不为1，删除失败"); }
    }

    @Override
    public void updateLabItem(CheckItem item) {
        int i = labTechMapper.updateCheckItem(item);
        if(i!=1){ throw new BusinessException("受影响的行数不为1，修改失败"); }
    }

    @Override
    public void updateActiveStatus(int itemId) {
        int i = labTechMapper.updateActiveStatus(itemId);
        if(i!=1){ throw new BusinessException("受影响的行数不为1，发布项目失败"); }
    }

    @Override
    public List<LabItemSampleVO> queryCheckItemList() {
        Map<String,Object> map = ThreadLocalUtil.get();
        String labNo = (String) map.get("工号");
        try {
            return labTechMapper.queryLabItemList(labNo);
        } catch (Exception e) {
            throw new RuntimeException("查询列表失败",e);
        }
    }

    @Override
    public CheckItem queryItemById(int itemId) {
        try {
            return labTechMapper.queryItemById(itemId);
        } catch (Exception e) {
            throw new RuntimeException("查询单个化验项目详情失败",e);
        }
    }
}
