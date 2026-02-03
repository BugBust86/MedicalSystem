package lds.com.medicalsystem.staff.labTech.mapper;

import lds.com.medicalsystem.common.MVC.CommonMapper;
import lds.com.medicalsystem.staff.labTech.PersonalDTO;
import lds.com.medicalsystem.staff.labTech.entity.LabItem;
import lds.com.medicalsystem.staff.labTech.vo.LabItemSampleVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

// 化验员业务的mapper层，公共区（common包）和化验员区（labTech包）的service层可以调用
public interface LabTechMapper extends CommonMapper {
    // 化验员的注册操作实际为修改操作，将psw的null变成”223344“（管理员的插入接口不插入密码)
    // 传入工号和新密码，修改密码
    @Update("update lab_tech set password=#{newPsw} where lab_no=#{labNo}")
    int labUpdate(String labNo, String newPsw);

    // --------更改个人中心区域（修稿邮箱、姓名、手机号）---------
    @Update("update lab_tech set lab_name = #{dto.labName}," +
            "phone=#{dto.newPhone},email=#{dto.email} where lab_no=#{dto.labNo};")
    int updatePersonalCentral(PersonalDTO dto);

    // --------检查化验项目区域---------
    // 增加体检化验项目
    @Insert("insert into lab_items (item_id, item_name, item_desc, item_place, start_time, end_time) " +
            "values (#{item.itemName},#{item.itemDesc},#{item.itemPlace},#{item.startTime},#{item.endTime});")
    int addCheckItem( @Param("item") LabItem item);
    // 查询所有的检查化验项目，列表返回项目名+开始时间、结束时间
    @Select("select item_name, start_time, end_time from lab_items;")
    List<LabItemSampleVO> queryLabItemList();
    // 根据项目名查询检查化验项目（不可能出现同名的项目，不方便用户理解查看）
    @Select("select * from lab_items where item_name=#{labName};")
    LabItem queryItemByName(String labName);
}
