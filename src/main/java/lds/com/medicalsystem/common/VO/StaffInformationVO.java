package lds.com.medicalsystem.common.VO;

import lombok.Data;

// 查看个人信息时返回给前端的对象
@Data
public class StaffInformationVO {
    // 前端显示头像
    private String pic;
    // 前端显示工号
    private String staffId;
    // 前端显示姓名
    private String name;
    // 前端显示手机号
    private String phone;
    // 前端显示邮箱
    private String email;


}
