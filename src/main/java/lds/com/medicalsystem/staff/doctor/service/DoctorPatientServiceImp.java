package lds.com.medicalsystem.staff.doctor.service;

import lds.com.medicalsystem.staff.doctor.VO.PatientReserveInfoVO;
import lds.com.medicalsystem.staff.doctor.mapper.DoctorPatientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorPatientServiceImp implements DoctorPatientService{
    @Autowired
    private DoctorPatientMapper doctorPatientMapper;
    // 查预约某个医生的所有患者信息
    @Override
    public List<PatientReserveInfoVO> patientReserveByDocNo(String doctorNo) {
        try {
            return doctorPatientMapper.patientReserveByDocNo(doctorNo);
        } catch (Exception e) {
            throw new RuntimeException("查询失败",e);
        }
    }


}
