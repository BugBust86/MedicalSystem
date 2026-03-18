package lds.com.medicalsystem.staff.admin.service;

import lds.com.medicalsystem.staff.admin.DTO.DocOtherInfoDTO;
import lds.com.medicalsystem.staff.admin.DTO.SearchTableDTO;
import lds.com.medicalsystem.staff.admin.DTO.WorkTableInsertDTO;
import lds.com.medicalsystem.staff.admin.DTO.WorkTableUpdateDTO;
import lds.com.medicalsystem.staff.admin.VO.PageResultVO;
import lds.com.medicalsystem.staff.admin.VO.WorkListVO;
import tools.jackson.databind.node.StringNode;

import java.util.List;

public interface AdminArrangeService {
    PageResultVO searchWorkList(int page, int pageSize, String deptName, String doctorName);

    DocOtherInfoDTO searchOtherByDocName(String docName);

    List<String> docNameListInDept(String deptName);

    List<String> docNameListInDeptSort(String deptSortName);

    void insertWorkInfo(WorkTableInsertDTO dto);

    void updateWorkInfo(WorkTableUpdateDTO dto);

    void deleteWorkInfo(Integer workTableId);
}
