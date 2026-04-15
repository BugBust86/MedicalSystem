package lds.com.medicalsystem.staff.doctor.service;

import lds.com.medicalsystem.common.utils.exception.BusinessException;
import lds.com.medicalsystem.staff.doctor.DTO.HistoryAddDTO;
import lds.com.medicalsystem.staff.doctor.VO.PatientReserveInfoVO;
import lds.com.medicalsystem.staff.doctor.mapper.DoctorPatientMapper;
import lds.com.medicalsystem.staff.doctor.utils.DoctorTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoctorPatientServiceImp implements DoctorPatientService{
    @Autowired
    private DoctorPatientMapper doctorPatientMapper;
    // 查预约某个医生的患者信息
    @Override
    public List<PatientReserveInfoVO> patientReserveByDocNo(String doctorNo) {
        try {
            return doctorPatientMapper.patientReserveByDocNo(doctorNo);
        } catch (Exception e) {
            throw new RuntimeException("查询失败",e);
        }
    }

    // 医生填写病历本
    @Override
    @Transactional
    public void doctorWriteHistory(HistoryAddDTO dto) {
        // 1. 插入病历记录到medical_histories表
        int rows = doctorPatientMapper.insertMedicalHistory(dto);
        if (rows <= 0) {
            throw new BusinessException("病历记录插入失败");
        }
        // 2. 更新预约状态为已接诊
        doctorPatientMapper.updateReservationReceived(dto.getReservationId());
    }
}
