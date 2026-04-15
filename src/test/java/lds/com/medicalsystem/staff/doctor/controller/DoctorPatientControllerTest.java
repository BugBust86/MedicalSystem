package lds.com.medicalsystem.staff.doctor.controller;

import lds.com.medicalsystem.common.VO.ResultVO;
import lds.com.medicalsystem.staff.doctor.DTO.HistoryAddDTO;
import lds.com.medicalsystem.staff.doctor.VO.PatientReserveInfoVO;
import lds.com.medicalsystem.staff.doctor.service.DoctorPatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * DoctorPatientController单元测试
 */
@ExtendWith(MockitoExtension.class)
class DoctorPatientControllerTest {

    @Mock
    private DoctorPatientService doctorPatientService;

    @InjectMocks
    private DoctorPatientController doctorPatientController;

    // ========== patientReserveFindByDocNo 测试 ==========

    @Test
    void patientReserveFindByDocNo_success() throws Exception {
        // given: 模拟ThreadLocal
        Map<String, Object> claims = new HashMap<>();
        claims.put("工号", "D001");

        ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();
        threadLocal.set(claims);

        var threadLocalField = lds.com.medicalsystem.common.utils.config.ThreadLocalUtil.class.getDeclaredField("threadLocal");
        threadLocalField.setAccessible(true);
        threadLocalField.set(null, threadLocal);

        try {
            List<PatientReserveInfoVO> patientList = Arrays.asList(
                createPatientReserveVO(1, "张三", "13800138000", "2024-01-01", "09:00")
            );
            when(doctorPatientService.patientReserveByDocNo(anyString())).thenReturn(patientList);

            // when
            ResponseEntity<ResultVO<List<PatientReserveInfoVO>>> response = doctorPatientController.patientReserveFindByDocNo();

            // then
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(1, response.getBody().getData().size());
        } finally {
            threadLocalField.set(null, null);
            threadLocal.remove();
        }
    }

    // ========== addMedicalHistory 测试 ==========

    @Test
    void addMedicalHistory_success() {
        // given
        HistoryAddDTO dto = new HistoryAddDTO();
        dto.setReservationId(1);
        dto.setMedicalHistory("感冒");
        dto.setPatientDescription("发烧");
        dto.setDoctorAdvice("多休息");

        doNothing().when(doctorPatientService).doctorWriteHistory(any(HistoryAddDTO.class));

        // when
        ResponseEntity<ResultVO<Void>> response = doctorPatientController.addMedicalHistory(dto);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isSuccess());
        assertEquals("填写成功", response.getBody().getMessage());
        verify(doctorPatientService).doctorWriteHistory(dto);
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
