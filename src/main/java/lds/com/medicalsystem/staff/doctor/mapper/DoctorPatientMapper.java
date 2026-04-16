package lds.com.medicalsystem.staff.doctor.mapper;

import lds.com.medicalsystem.staff.doctor.DTO.HistoryAddDTO;
import lds.com.medicalsystem.staff.doctor.DTO.HistoryUpdateDTO;
import lds.com.medicalsystem.staff.doctor.VO.MedicalHistoryDetailVO;
import lds.com.medicalsystem.staff.doctor.VO.PatientReserveInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DoctorPatientMapper {
    // 查预约某个医生的患者信息的列表
    List<PatientReserveInfoVO> patientReserveByDocNo(String doctorNo);

    // 插入病历记录
    int insertMedicalHistory(HistoryAddDTO dto);

    // 更新预约接诊状态
    int updateReservationReceived(Integer reservationId);

    // 检查病历是否已存在（根据reservationId和cardId）
    int checkMedicalHistoryExists(@Param("reservationId") Integer reservationId, @Param("cardId") Integer cardId);

    // 检查病历是否已存在（只根据reservationId）
    int checkMedicalHistoryExistsByReservationId(Integer reservationId);

    // 查看病历本详情（is_receive=1才能查看）
    MedicalHistoryDetailVO getMedicalHistoryDetail(Integer reservationId);

    // 更新病历本
    int updateMedicalHistory(HistoryUpdateDTO dto);

}
