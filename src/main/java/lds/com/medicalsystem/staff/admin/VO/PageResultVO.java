package lds.com.medicalsystem.staff.admin.VO;

import lombok.Data;

import java.util.List;

@Data
public class PageResultVO {
    private List<WorkListVO> data;
    private int total;  // 总记录数
//    private int page;  // 当前页码
//    private int pageSize;  // 每页包含多少数据
    private Boolean hasNext; // 是否还有下一页
}
