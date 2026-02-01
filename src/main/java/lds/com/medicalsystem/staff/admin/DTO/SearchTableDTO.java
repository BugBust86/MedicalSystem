package lds.com.medicalsystem.staff.admin.DTO;

import lombok.Data;

@Data
public class SearchTableDTO {
    private int page;   // 当前页码
    private int pageSize;  // 页面大小
    private String deptSort;    //科室分类名
    private String deptName;    //具体科室名
}
