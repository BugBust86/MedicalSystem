package lds.com.medicalsystem.staff.doctor.service;

import lds.com.medicalsystem.staff.admin.VO.WorkListVO;
import lds.com.medicalsystem.staff.doctor.pojo.InfoDTO;
import lds.com.medicalsystem.staff.doctor.pojo.PatientInfoDTO;
import lds.com.medicalsystem.staff.doctor.pojo.PatientInfoVO;

import java.util.List;

public interface DoctorPersonalService {
    // 传入医生工号，返回值班日期、时间段、工号、最大预约人数、已预约人数
    List<WorkListVO> searchDocWorkTable(String doctorNo);

    // 传入医生工号、时间段、值班日期，返回当下全部患者对象列表
    // 点击全部患者，查固定医生固定日期固定时间段的患者姓名+卡号对象列表
    List<PatientInfoDTO> searchAllPatient(InfoDTO dto);

    // 点击具体的患者名字，查看病历本，显示就诊卡上的患者的详细信息（背后传入cardId，查全部患者时返回的)
    PatientInfoVO getPatientInfo(int cardId);

    // 修改医生的邮箱、擅长信息
    void update(String email,String specialty);
}
