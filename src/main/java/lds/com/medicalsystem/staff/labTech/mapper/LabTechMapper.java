package lds.com.medicalsystem.staff.labTech.mapper;

import lds.com.medicalsystem.common.CommonMapper;
import org.apache.ibatis.annotations.Update;

// 化验员业务的mapper层，公共区（common包）和化验员区（labTech包）的service层可以调用
public interface LabTechMapper extends CommonMapper {
    // 化验员的注册操作实际为修改操作，将psw的null变成”223344“（管理员的插入接口不插入密码)
    // 传入工号和新密码，修改密码
    @Update("update lab_tech set password=#{newPsw} where lab_no=#{labNo}")
    int labUpdate(String labNo, String newPsw);

}
