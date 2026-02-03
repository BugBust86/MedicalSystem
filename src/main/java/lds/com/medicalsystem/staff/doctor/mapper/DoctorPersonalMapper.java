package lds.com.medicalsystem.staff.doctor.mapper;

import lds.com.medicalsystem.staff.admin.VO.WorkListVO;
import lds.com.medicalsystem.staff.doctor.DTO.InfoDTO;
import lds.com.medicalsystem.staff.doctor.DTO.PatientInfoDTO;
import lds.com.medicalsystem.staff.doctor.VO.PatientInfoVO;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DoctorPersonalMapper {
    // 传入医生工号，查询值班表（包含值班日期、时间段、工号、最大预约人数,已预约人数
    List<WorkListVO> searchDoctorWorkTable(String doctorNO);

    // 点击全部患者，查固定医生固定日期固定时间段的患者姓名+卡号对象列表
    List<PatientInfoDTO> searchPatientNameList(InfoDTO dto);

    // 点对应的患者名，查看病历本，即显示患者详细信息
    @Select("select patient_name,id_number,gender,age from medical_cards where card_id=#{cardId}")
    PatientInfoVO getPatientInfo(int cardId);

    // 动态sql更新医生表的邮箱和头像
    int update(String doctorNo,String email,String doctorPic);
    // 更新医生表的头像url
    @Update("")
    int updateURL(String doctorPic);
}
