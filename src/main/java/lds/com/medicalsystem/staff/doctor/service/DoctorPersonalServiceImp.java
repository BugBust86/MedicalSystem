package lds.com.medicalsystem.staff.doctor.service;

import lds.com.medicalsystem.common.utils.config.ThreadLocalUtil;
import lds.com.medicalsystem.staff.admin.VO.WorkListVO;
import lds.com.medicalsystem.staff.doctor.DTO.InfoDTO;
import lds.com.medicalsystem.staff.doctor.DTO.PatientInfoDTO;
import lds.com.medicalsystem.staff.doctor.VO.PatientInfoVO;
import lds.com.medicalsystem.staff.doctor.mapper.DoctorPersonalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DoctorPersonalServiceImp implements DoctorPersonalService {
    @Autowired
    private DoctorPersonalMapper doctorPersonalMapper;
    // 传入医生工号，返回值班日期、时间段、工号、最大预约人数、已预约人数
    @Override
    public List<WorkListVO> searchDocWorkTable(String doctorNo) {
        try {
            return doctorPersonalMapper.searchDoctorWorkTable(doctorNo);
        } catch (Exception e) {
            throw new RuntimeException("查询失败",e);
        }
    }
    @Override
    // 传入医生工号、时间段、值班日期，返回当下全部患者对象列表
    // 点击全部患者，查固定医生固定日期固定时间段的患者姓名+卡号对象列表
    public List<PatientInfoDTO> searchAllPatient(InfoDTO dto){
        try {
            return doctorPersonalMapper.searchPatientNameList(dto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    // 点击具体的患者名字（患者可能重名所以必须是id），查看病历本，显示就诊卡上的患者的详细信息（背后传入cardId，查全部患者时返回的)
    public PatientInfoVO getPatientInfo(int cardId){
        try {
            return doctorPersonalMapper.getPatientInfo(cardId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void update(String email, String specialty) {
        Map<String,Object> map = ThreadLocalUtil.get();
        String doctorNo = (String)map.get("工号");
        if(doctorPersonalMapper.update(doctorNo,email,specialty)!=1){
            throw new RuntimeException("修改更新失败");
        }
    }
}