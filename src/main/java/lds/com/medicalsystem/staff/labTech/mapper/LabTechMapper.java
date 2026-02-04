package lds.com.medicalsystem.staff.labTech.mapper;

import lds.com.medicalsystem.common.MVC.CommonMapper;
import lds.com.medicalsystem.staff.labTech.PersonalDTO;
import lds.com.medicalsystem.staff.labTech.entity.CheckItem;
import lds.com.medicalsystem.staff.labTech.vo.LabItemSampleVO;
import org.apache.ibatis.annotations.*;

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
    @Insert("insert into medical_db.check_items (item_id, item_name, item_desc, item_place, start_time, end_time,lab_no,reserve_max) " +
            "values (#{item.itemName},#{item.itemDesc},#{item.itemPlace},#{item.startTime},#{item.endTime},#{item.labNo},#{item.reserveMax});")
    int addCheckItem( @Param("item") CheckItem item);
    // 删除检查化验项目，根据id（或者项目名），根据id删快一点
    @Delete("delete from check_items where item_id=#{itemId};")
    int deleteCheckItem(int itemId);
    // 修改检查化验项目，根据id
    @Update("update check_items set item_name=#{item.itemName}, item_desc=#{item.itemDesc}, item_place=#{item.itemPlace}" +
            ", start_time=#{item.startTime}, end_time=#{item.endTime},reserve_max=#{item.reserveMax} where item_id=#{item.itemId};")
    int updateCheckItem(CheckItem item);
    // 查询某个化验员下所有的检查化验项目，列表返回id+项目名+开始时间、结束时间
    @Select("select item_id, item_name, start_time, end_time from check_items where lab_no=#{labNo};")
    List<LabItemSampleVO> queryLabItemList(String labNo);
    // 根据项目id查询项目的全部内容（不可能出现同名的项目，不方便用户理解查看，但项目id查快点）
    @Select("select * from medical_db.check_items where item_id=#{itemId};")
    CheckItem queryItemById(int itemId);
    // 修改item的活跃状态，改成1（发布，用户可查）
    @Update("update check_items set is_active = 1 where item_id=#{itemId};")
    int updateActiveStatus(int itemId);
}
