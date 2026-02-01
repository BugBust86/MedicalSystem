package lds.com.medicalsystem.staff.admin.service;

import lds.com.medicalsystem.common.utils.exception.BusinessException;
import lds.com.medicalsystem.staff.admin.DTO.DocOtherInfoDTO;
import lds.com.medicalsystem.staff.admin.DTO.SearchTableDTO;
import lds.com.medicalsystem.staff.admin.DTO.WorkTableInsertDTO;
import lds.com.medicalsystem.staff.admin.DTO.WorkTableUpdateDTO;
import lds.com.medicalsystem.staff.admin.VO.PageResultVO;
import lds.com.medicalsystem.staff.admin.VO.WorkListVO;
import lds.com.medicalsystem.staff.admin.mapper.AdminArrangeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminArrangeServiceImp implements AdminArrangeService {
    private final AdminArrangeMapper adminArrangeMapper;
    public AdminArrangeServiceImp(AdminArrangeMapper adminArrangeMapper) {
        this.adminArrangeMapper = adminArrangeMapper;
    }
    @Override
    public PageResultVO searchWorkList(SearchTableDTO dto) {
        List<WorkListVO> data = null;
        int total = 0;
        try {
            data = adminArrangeMapper.searchWorkListByCondition(dto);
            total = adminArrangeMapper.countWorkListByCondition(dto);
        } catch (Exception e) {
            throw new RuntimeException("查询失败！",e);
        }
        PageResultVO result = new PageResultVO();
        result.setData(data);
        result.setTotal(total);
        // 通过列表data的长度判断是否有下一页，若小于，一定没有下一页;等于未知，大于不可能
        if(data.size()<dto.getPageSize() || total/dto.getPageSize()==dto.getPage()){
            result.setHasNext(false);
        } else result.setHasNext(true);
        return result;
    }
    @Override
    public DocOtherInfoDTO searchOtherByDocName(String docName) {
        try {
            return adminArrangeMapper.searchNoDNameByDocName(docName);
        } catch (Exception e) {
            throw new RuntimeException("查询工号、科室名失败",e);
        }
    }
    @Override
    public List<String> docNameListInDept(String deptName) {
        try {
            return adminArrangeMapper.docNameListInDept(deptName);
        } catch (Exception e) {
            throw new RuntimeException("返回某科室下的医生名列表失败",e);
        }
    }
    @Override
    public List<String> docNameListInDeptSort(String deptSortName) {
        try {
            return adminArrangeMapper.docNameListInDeptSort(deptSortName);
        } catch (Exception e) {
            throw new RuntimeException("返回某科室分类下的全部医生名列表失败",e);
        }
    }
    @Override
    public void insertWorkInfo(WorkTableInsertDTO dto) {
        try {
            int i = adminArrangeMapper.insertWorkList(dto);
            if(i!=1){
                System.out.println(i);
                throw new BusinessException("受影响行数不为1，插入失败");
            }
        } catch (Exception e) {
            throw new RuntimeException("插入值班信息失败",e);
        }
    }
    @Override
    public void updateWorkInfo(WorkTableUpdateDTO dto) {
        try {
            if(adminArrangeMapper.updateWorkList(dto)!=1){
                System.out.println(adminArrangeMapper.updateWorkList(dto));
                throw new BusinessException("受影响的行数不为1，修改失败");
            };
        } catch (Exception e) {
            throw new RuntimeException("修改操作失败",e);
        }
    }
    @Override
    public void deleteWorkInfo(Integer workTableId) {
        try {
            int i = adminArrangeMapper.deleteWorkList(workTableId);
            if(i!=1){
                System.out.println(i);
                throw new BusinessException("受影响的行数不为1，删除失败");
            }
        } catch (Exception e) {
            throw new RuntimeException("删除操作失败",e);
        }
    }
}
