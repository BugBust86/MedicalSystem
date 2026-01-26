package lds.com.medicalsystem.staff.admin.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AdminMapper {
    // 传入工号，修改管理员账号的密码
    @Update("update admin set password=#{newPsw} where admin_no=#{adminNo}")
    void update(String adminNo, String newPsw);
}
