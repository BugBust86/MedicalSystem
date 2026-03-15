package lds.com.medicalsystem.staff.doctor.mapper;

import lds.com.medicalsystem.staff.doctor.VO.PatientReserveInfoVO;

import java.util.List;

public interface DoctorPatientMapper {
    // 查预约某个医生的患者信息的列表
    List<PatientReserveInfoVO> patientReserveByDocNo(String doctorNo);


}
