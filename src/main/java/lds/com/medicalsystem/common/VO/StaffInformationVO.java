package lds.com.medicalsystem.common.VO;

import lds.com.medicalsystem.staff.doctor.entity.DoctorTitle;
import lombok.Data;

// 查看个人信息时返回给前端的对象
@Data
public class StaffInformationVO {
    // 前端显示头像，可为空
    private String pic;
    // 前端显示工号
    private String staffId;
    // 前端显示姓名
    private String name;
    // 前端显示手机号
    private String phone;
    // 前端显示邮箱
    private String email;
    // 职称，管理员和化验员没有设为null，返回给前端的时候前端代码不处理这一条即可
    private DoctorTitle title;
    // 角色，说不定前端代码可以用到
    private String role;
}
