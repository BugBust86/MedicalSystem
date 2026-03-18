package lds.com.medicalsystem.staff.admin.DTO;

import lombok.Data;

@Data
public class SearchTableDTO {
    private int page;   // 当前页码
    private int pageSize;  // 页面大小
    private int offset;    // 分页偏移量（自动计算：(page-1) * pageSize）
    private String doctorName;    //医生名
    private String deptName;    //具体科室名
}
