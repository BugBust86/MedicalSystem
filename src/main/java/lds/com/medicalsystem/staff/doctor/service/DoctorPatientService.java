package lds.com.medicalsystem.staff.doctor.service;

import lds.com.medicalsystem.staff.doctor.DTO.HistoryAddDTO;
import lds.com.medicalsystem.staff.doctor.VO.PatientReserveInfoVO;

import java.util.List;

public interface DoctorPatientService {
    // 查预约某个医生的所有患者信息的列表
    List<PatientReserveInfoVO> patientReserveByDocNo(String doctorNo);

    // 医生填写病历本
    void doctorWriteHistory(HistoryAddDTO dto);
}
