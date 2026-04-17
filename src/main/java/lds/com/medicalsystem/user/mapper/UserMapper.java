package lds.com.medicalsystem.user.mapper;

import lds.com.medicalsystem.user.VO.CardInfoVO;
import lds.com.medicalsystem.user.VO.UserInfoVO;
import lds.com.medicalsystem.user.DTO.MedicalCardAddDTO;
import lds.com.medicalsystem.user.DTO.MedicalCardUpdateDTO;
import lds.com.medicalsystem.user.VO.MedicalCardDetailVO;
import lds.com.medicalsystem.user.VO.PatientRecordDetailVO;
import lds.com.medicalsystem.user.VO.PatientRecordVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {
    //判断手机号是否在表中
    @Select("select exists(select 1 from users where phone=#{phone})")
    Boolean checkPhoneExists(String phone);
    //通过手机号查id
    @Select("select user_id from users where phone=#{phone}")
    int findIdByPhone(String phone);
    //用户注册
    @Insert("insert into users(phone, password, user_name) VALUES(#{phone},#{psw},#{userName}) ")
    int userRegister(@Param("phone") String phone, @Param("psw") String psw, @Param("userName") String userName); // @Param注解让Mybatis区分多个参数的对应关系
    // 用户登录，根据手机号查密码，Token的负载部分装用户手机号
    @Select("select password from users where phone = #{phone}")
    String userLoginSelect(String phone);
    // 通过userId查询user对象
    @Select("select user_name, phone, sex from users where user_id = #{userId}")
    UserInfoVO selectByUserId(int userId);

    //判断身份证号是否存在于就诊卡表中（可不加，因为可能一个病人有多个家属为他建立了就诊卡）
    //用户添加就诊卡
    @Insert("insert into medical_cards(patient_name,id_number,gender,age," +
            "relationship,contact_phone,user_id)" +
            " VALUES(#{patientName},#{idNumber}" +
            ",#{gender},#{age},#{relationship},#{contactPhone},#{userId}) ")
    int addMedicalCard(MedicalCardAddDTO mc);

    // 通过userId查就诊卡列表信息
    @Select("select card_id,patient_name,relationship from medical_cards where user_id = #{userId};")
    List<CardInfoVO> searchCardList(int userId);

    // 通过cardId查询就诊卡详情
    @Select("select card_id, patient_name, id_number, gender, age, relationship, contact_phone, user_id from medical_cards where card_id = #{cardId}")
    MedicalCardDetailVO selectMedicalCardById(int cardId);

    // 修改就诊卡信息
    @Update("update medical_cards set patient_name = #{patientName}, id_number = #{idNumber}, gender = #{gender}, age = #{age}, relationship = #{relationship}, contact_phone = #{contactPhone} where card_id = #{cardId}")
    int updateMedicalCard(MedicalCardUpdateDTO dto);

    // 查询用户的就诊记录列表
    @Select("select mr.record_id as recordId, mr.appointment_date as appointmentDate, " +
            "r.reserve_time as reserveTime, mc.patient_name as patientName, " +
            "d.doctor_name as doctorName, dept.dept_name as deptName " +
            "from medical_records mr " +
            "inner join reservation r on mr.reservation_id = r.id " +
            "inner join medical_cards mc on mr.card_id = mc.card_id " +
            "inner join doctor d on mr.doctor_no = d.doctor_no " +
            "inner join dept on d.dept_id = dept.dept_id " +
            "where mc.user_id = #{userId} " +
            "order by mr.appointment_date desc, mr.created_at desc")
    List<PatientRecordVO> selectRecordListByUserId(int userId);

    // 查询就诊记录详情
    @Select("select mr.record_id as recordId, mr.appointment_date as appointmentDate, " +
            "r.reserve_time as reserveTime, mc.patient_name as patientName, " +
            "d.doctor_name as doctorName, dept.dept_name as deptName, " +
            "mc.contact_phone as contactPhone, " +
            "mh.medical_history, mh.patient_description, mh.doctor_advice, " +
            "mh.created_at as historyCreateAt " +
            "from medical_records mr " +
            "inner join reservation r on mr.reservation_id = r.id " +
            "inner join medical_cards mc on mr.card_id = mc.card_id " +
            "inner join doctor d on mr.doctor_no = d.doctor_no " +
            "inner join dept on d.dept_id = dept.dept_id " +
            "left join medical_histories mh on mr.record_id = mh.record_id " +
            "where mr.record_id = #{recordId}")
    PatientRecordDetailVO selectRecordDetailById(int recordId);
}
