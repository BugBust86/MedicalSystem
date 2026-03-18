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
    public PageResultVO searchWorkList(int page, int pageSize, String deptName, String doctorName) {
        System.out.println("[Service] ========== 开始查询值班列表 ==========");
        System.out.println("[Service] 参数：page=" + page + ", pageSize=" + pageSize + ", deptName=" + deptName + ", deptSort=" + doctorName);
        
        List<WorkListVO> data = null;
        int total = 0;
        try {
            // 构造查询参数对象（仅用于传递给 Mapper，生命周期仅限于本方法）
            System.out.println("[Service] 1. 开始构造 DTO 对象...");
            SearchTableDTO dto = new SearchTableDTO();
            dto.setPage(page);
            dto.setPageSize(pageSize);
            dto.setDeptName(deptName);
            dto.setDoctorName(doctorName);
            // 计算分页偏移量（从 0 开始）
            dto.setOffset((page - 1) * pageSize);
            System.out.println("[Service] 2. DTO 构造完成，offset=" + dto.getOffset());

            System.out.println("[Service] 3. 准备调用 Mapper 查询数据列表...");
            data = adminArrangeMapper.searchWorkListByCondition(dto);
            System.out.println("[Service] 4. 数据列表查询完成，条数：" + (data == null ? 0 : data.size()));

            System.out.println("[Service] 5. 准备调用 Mapper 查询总数...");
            total = adminArrangeMapper.countWorkListByCondition(dto);
            System.out.println("[Service] 6. 总数查询完成：" + total);
        } catch (Exception e) {
            System.out.println("[Service] ========== 查询发生异常 ==========");
            System.out.println("[Service] 异常类型：" + e.getClass().getName());
            System.out.println("[Service] 异常信息：" + e.getMessage());
            System.out.println("[Service] 堆栈跟踪:");
            e.printStackTrace();
            System.out.println("[Service] ==================================");
            throw new RuntimeException("查询失败！",e);
        }
        
        System.out.println("[Service] 7. 开始构造返回结果...");
        PageResultVO result = new PageResultVO();
        result.setData(data);
        result.setTotal(total);
        
        // 通过列表 data 的长度判断是否有下一页，若小于，一定没有下一页;等于未知，大于不可能
        if(data.size()<pageSize || total/pageSize==page){
            result.setHasNext(false);
            System.out.println("[Service] 8. 判断结果：没有下一页 (hasNext=false)");
        } else {
            result.setHasNext(true);
            System.out.println("[Service] 8. 判断结果：有下一页 (hasNext=true)");
        }
        
        System.out.println("[Service] ========== 查询完成，返回结果 ==========");
        System.out.println("[Service] data 大小：" + data.size() + ", total: " + total + ", hasNext: " + result.getHasNext());
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
        System.out.println("[Service] ========== 开始更新值班信息 ==========");
        System.out.println("[Service] 接收到的 DTO: " + dto);
        System.out.println("[Service] workTableId: " + dto.getId());
        System.out.println("[Service] workDate: " + dto.getWorkDate());
        System.out.println("[Service] workTime: " + dto.getWorkTime());
        System.out.println("[Service] doctorNo: " + dto.getDoctorNo());
        System.out.println("[Service] reserveMax: " + dto.getReserveMax());
            
        try {
            System.out.println("[Service] 准备调用 Mapper 更新...");
            int result = adminArrangeMapper.updateWorkList(dto);
            System.out.println("[Service] Mapper 返回结果：" + result);
                
            if(result != 1){
                System.out.println("[Service] 错误：受影响的行数不为 1，实际为：" + result);
                throw new BusinessException("受影响的行数不为 1，修改失败，受影响行数：" + result);
            }
            System.out.println("[Service] ========== 更新成功 ==========");
        } catch (BusinessException e) {
            System.out.println("[Service] 业务异常：" + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("[Service] ========== 更新发生异常 ==========");
            System.out.println("[Service] 异常类型：" + e.getClass().getName());
            System.out.println("[Service] 异常信息：" + e.getMessage());
            e.printStackTrace();
            System.out.println("[Service] ==================================");
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
