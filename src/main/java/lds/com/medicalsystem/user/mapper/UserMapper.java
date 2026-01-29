package lds.com.medicalsystem.user.mapper;

import lds.com.medicalsystem.user.entity.MedicalCard;
import lds.com.medicalsystem.user.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    //判断手机号是否在表中
    @Select("select exists(select 1 from users where phone=#{phone})")
    Boolean checkPhoneExists(String phone);
    //通过手机号查id
    @Select("select user_id from users where phone=#{phone}")
    String findIdByPhone(String phone);
    //用户注册
    @Insert("insert into users(phone, password) VALUES(#{phone},#{psw}) ")
    // @Param注解让Mybatis区分多个参数的对应关系
    int userRegister(@Param("phone") String phone, @Param("psw") String psw);
    // 用户登录，根据手机号查密码，Token的负载部分装用户手机号
    @Select("select password from users where phone = #{phone}")
    String userLoginSelect(String phone);
    // 通过手机号查询user对象
    @Select("select * from users where phone = #{phone}")
    User selectByPhone(String phone);

    //判断身份证号是否存在于就诊卡表中（可不加，因为可能一个病人有多个家属为他建立了就诊卡）
    //用户添加就诊卡
    @Insert("insert into medical_cards(patient_name,id_number,gender,age," +
            "relationship,contact_phone,user_id)" +
            " VALUES(#{patientName},#{idNumber}" +
            ",#{gender},#{age},#{relationship},#{contactPhone},#{userId}) ")
    int addMedicalCard(MedicalCard mc);
}
