package lds.com.medicalsystem.staff.doctor.service;

import lds.com.medicalsystem.common.utils.exception.BusinessException;
import lds.com.medicalsystem.staff.doctor.DTO.HistoryAddDTO;
import lds.com.medicalsystem.staff.doctor.VO.PatientReserveInfoVO;
import lds.com.medicalsystem.staff.doctor.mapper.DoctorPatientMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * DoctorPatientService单元测试
 */
@ExtendWith(MockitoExtension.class)
class DoctorPatientServiceTest {

    @Mock
    private DoctorPatientMapper doctorPatientMapper;

    @InjectMocks
    private DoctorPatientServiceImp doctorPatientService;

    @BeforeEach
    void setUp() {
        doctorPatientService = new DoctorPatientServiceImp();
    }

    // ========== patientReserveByDocNo 测试 ==========

    @Test
    void patientReserveByDocNo_success() {
        // given
        List<PatientReserveInfoVO> expectedList = Arrays.asList(
            createPatientReserveVO(1, "张三", "13800138000", "2024-01-01", "09:00"),
            createPatientReserveVO(2, "李四", "13900139000", "2024-01-01", "10:00")
        );
        when(doctorPatientMapper.patientReserveByDocNo(anyString())).thenReturn(expectedList);

        // when
        List<PatientReserveInfoVO> result = doctorPatientService.patientReserveByDocNo("D001");

        // then
        assertEquals(2, result.size());
        verify(doctorPatientMapper).patientReserveByDocNo("D001");
    }

    @Test
    void patientReserveByDocNo_empty() {
        // given
        when(doctorPatientMapper.patientReserveByDocNo(anyString())).thenReturn(Collections.emptyList());

        // when
        List<PatientReserveInfoVO> result = doctorPatientService.patientReserveByDocNo("D001");

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    void patientReserveByDocNo_exception() {
        // given
        when(doctorPatientMapper.patientReserveByDocNo(anyString()))
            .thenThrow(new RuntimeException("database error"));

        // then
        assertThrows(RuntimeException.class, () ->
            doctorPatientService.patientReserveByDocNo("D001")
        );
    }

    // ========== doctorWriteHistory 测试 ==========

    @Test
    void doctorWriteHistory_success() {
        // given
        HistoryAddDTO dto = new HistoryAddDTO();
        dto.setReservationId(1);
        dto.setMedicalHistory("感冒");
        dto.setPatientDescription("发烧、咳嗽");
        dto.setDoctorAdvice("多休息，多喝水");

        when(doctorPatientMapper.insertMedicalHistory(any(HistoryAddDTO.class))).thenReturn(1);
        doNothing().when(doctorPatientMapper).updateReservationReceived(anyInt());

        // when & then
        assertDoesNotThrow(() -> doctorPatientService.doctorWriteHistory(dto));

        verify(doctorPatientMapper).insertMedicalHistory(dto);
        verify(doctorPatientMapper).updateReservationReceived(1);
    }

    @Test
    void doctorWriteHistory_insertFail() {
        // given
        HistoryAddDTO dto = new HistoryAddDTO();
        dto.setReservationId(1);
        when(doctorPatientMapper.insertMedicalHistory(any(HistoryAddDTO.class))).thenReturn(0);

        // then
        assertThrows(BusinessException.class, () ->
            doctorPatientService.doctorWriteHistory(dto)
        );
    }

    // ========== 辅助方法 ==========

    private PatientReserveInfoVO createPatientReserveVO(int reservationId, String patientName,
            String phone, String reserveDate, String reserveTime) {
        PatientReserveInfoVO vo = new PatientReserveInfoVO();
        vo.setReservationId(reservationId);
        vo.setPatientName(patientName);
        vo.setPhone(phone);
        vo.setReserveDate(reserveDate);
        vo.setReserveTime(reserveTime);
        return vo;
    }
}
