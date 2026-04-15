package lds.com.medicalsystem.common.MVC;

import lds.com.medicalsystem.common.DTO.InnerLoginDTO;
import lds.com.medicalsystem.common.DTO.InnerRegisterDTO;
import lds.com.medicalsystem.common.DTO.UpdatePswDTO;
import lds.com.medicalsystem.common.VO.ResultVO;
import lds.com.medicalsystem.common.VO.StaffInformationVO;
import org.junit.jupiter.api.BeforeEach;
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
 * StaffController单元测试
 */
@ExtendWith(MockitoExtension.class)
class StaffControllerTest {

    @Mock
    private StaffService staffService;

    @InjectMocks
    private StaffController staffController;

    @BeforeEach
    void setUp() throws Exception {
        // 模拟ThreadLocal
        Map<String, Object> claims = new HashMap<>();
        claims.put("工号", "D001");
        claims.put("role", "医生");

        ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();
        threadLocal.set(claims);

        var threadLocalField = lds.com.medicalsystem.common.utils.config.ThreadLocalUtil.class.getDeclaredField("threadLocal");
        threadLocalField.setAccessible(true);
        threadLocalField.set(null, threadLocal);
    }

    // ========== staffRegisterBySelf 测试 ==========

    @Test
    void staffRegisterBySelf_success() {
        // given
        InnerRegisterDTO dto = new InnerRegisterDTO();
        dto.setStaffId("D001");
        dto.setName("张医生");
        dto.setRole("医生");
        doNothing().when(staffService).staffRegisterBySelf(any(InnerRegisterDTO.class));

        // when
        ResponseEntity<ResultVO<Void>> response = staffController.staffRegisterBySelf(dto);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("注册成功", response.getBody().getMessage());
    }

    // ========== staffLogin 测试 ==========

    @Test
    void staffLogin_success() {
        // given
        InnerLoginDTO dto = new InnerLoginDTO();
        dto.setStaffId("D001");
        dto.setPassword("123456");
        dto.setRole("医生");

        Map<String, String> loginInfo = new HashMap<>();
        loginInfo.put("token", "token123");
        loginInfo.put("name", "张医生");
        loginInfo.put("role", "医生");

        ResultVO<Map<String, String>> expectedResult = ResultVO.success("登录成功", loginInfo);
        when(staffService.staffLogin(any(InnerLoginDTO.class))).thenReturn(expectedResult);

        // when
        ResponseEntity<ResultVO<Map<String, String>>> response = staffController.staffLogin(dto);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody().getData().get("token"));
    }

    // ========== staffInfo 测试 ==========

    @Test
    void staffInfo_success() {
        // given
        StaffInformationVO staffInfo = new StaffInformationVO();
        staffInfo.setStaffId("D001");
        staffInfo.setName("张医生");
        staffInfo.setRole("医生");
        when(staffService.staffInfo(anyString(), anyString())).thenReturn(staffInfo);

        // when
        ResponseEntity<ResultVO<StaffInformationVO>> response = staffController.staffInfo();

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("D001", response.getBody().getData().getStaffId());
    }

    // ========== updateStaffPsw 测试 ==========

    @Test
    void updateStaffPsw_success() {
        // given
        UpdatePswDTO dto = new UpdatePswDTO();
        dto.setOldPsw("123456");
        dto.setNewPsw("654321");
        doNothing().when(staffService).staffUpdatePsw(any(UpdatePswDTO.class));

        // when
        ResponseEntity<ResultVO<Void>> response = staffController.updateStaffPsw(dto);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("修改成功", response.getBody().getMessage());
    }

    @Test
    void updateStaffPsw_fail() {
        // given
        UpdatePswDTO dto = new UpdatePswDTO();
        dto.setOldPsw("123456");
        dto.setNewPsw("654321");
        doThrow(new RuntimeException("修改失败")).when(staffService).staffUpdatePsw(any(UpdatePswDTO.class));

        // then
        assertThrows(RuntimeException.class, () -> staffController.updateStaffPsw(dto));
    }
}
