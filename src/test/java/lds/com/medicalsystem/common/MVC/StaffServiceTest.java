package lds.com.medicalsystem.common.MVC;

import lds.com.medicalsystem.common.DTO.InnerLoginDTO;
import lds.com.medicalsystem.common.DTO.InnerRegisterDTO;
import lds.com.medicalsystem.common.DTO.UpdatePswDTO;
import lds.com.medicalsystem.common.VO.ResultVO;
import lds.com.medicalsystem.common.VO.StaffInformationVO;
import lds.com.medicalsystem.common.utils.exception.BusinessException;
import lds.com.medicalsystem.staff.admin.entity.Admin;
import lds.com.medicalsystem.staff.admin.mapper.AdminMapper;
import lds.com.medicalsystem.staff.doctor.entity.Doctor;
import lds.com.medicalsystem.staff.doctor.mapper.DoctorMapper;
import lds.com.medicalsystem.staff.labTech.entity.LabTech;
import lds.com.medicalsystem.staff.labTech.mapper.LabTechMapper;
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
 * StaffService单元测试
 */
@ExtendWith(MockitoExtension.class)
class StaffServiceTest {

    @Mock
    private DoctorMapper doctorMapper;

    @Mock
    private LabTechMapper labTechMapper;

    @Mock
    private AdminMapper adminMapper;

    @InjectMocks
    private StaffServiceImp staffService;

    @BeforeEach
    void setUp() {
        staffService = new StaffServiceImp(doctorMapper, labTechMapper, adminMapper);
    }

    // ========== staffRegisterBySelf 测试 ==========

    @Test
    void staffRegisterBySelf_doctor_success() {
        // given
        InnerRegisterDTO dto = new InnerRegisterDTO();
        dto.setStaffId("D001");
        dto.setName("张医生");
        dto.setPhone("13800138000");
        dto.setPassword("123456");
        dto.setRole("医生");

        Doctor doctor = new Doctor();
        doctor.setDoctorNo("D001");
        doctor.setDoctorName("张医生");
        doctor.setPhone("13800138000");

        when(doctorMapper.selectDoctorByNo(anyString())).thenReturn(doctor);
        when(doctorMapper.doctorUpdate(anyString(), anyString())).thenReturn(1);

        // when & then
        assertDoesNotThrow(() -> staffService.staffRegisterBySelf(dto));
    }

    @Test
    void staffRegisterBySelf_doctor_notExist() {
        // given
        InnerRegisterDTO dto = new InnerRegisterDTO();
        dto.setStaffId("D001");
        dto.setRole("医生");
        when(doctorMapper.selectDoctorByNo(anyString())).thenReturn(null);

        // then
        assertThrows(BusinessException.class, () ->
            staffService.staffRegisterBySelf(dto)
        );
    }

    @Test
    void staffRegisterBySelf_labTech_success() {
        // given
        InnerRegisterDTO dto = new InnerRegisterDTO();
        dto.setStaffId("L001");
        dto.setName("李化验员");
        dto.setPhone("13800138000");
        dto.setPassword("123456");
        dto.setRole("化验员");

        LabTech labTech = new LabTech();
        labTech.setLabNo("L001");
        labTech.setLabName("李化验员");
        labTech.setPhone("13800138000");

        when(labTechMapper.selectLabTechByNo(anyString())).thenReturn(labTech);
        when(labTechMapper.labUpdate(anyString(), anyString())).thenReturn(1);

        // when & then
        assertDoesNotThrow(() -> staffService.staffRegisterBySelf(dto));
    }

    @Test
    void staffRegisterBySelf_admin_notAllowed() {
        // given
        InnerRegisterDTO dto = new InnerRegisterDTO();
        dto.setRole("管理员");

        // then
        assertThrows(IllegalArgumentException.class, () ->
            staffService.staffRegisterBySelf(dto)
        );
    }

    // ========== staffLogin 测试 ==========

    @Test
    void staffLogin_doctor_success() {
        // given
        InnerLoginDTO dto = new InnerLoginDTO();
        dto.setStaffId("D001");
        dto.setPassword("123456");
        dto.setRole("医生");

        Doctor doctor = new Doctor();
        doctor.setDoctorNo("D001");
        doctor.setDoctorName("张医生");
        doctor.setPassword("123456");
        doctor.setRole("医生");

        when(doctorMapper.selectDoctorByNo(anyString())).thenReturn(doctor);

        // when
        ResultVO<Map<String, String>> result = staffService.staffLogin(dto);

        // then
        assertTrue(result.isSuccess());
        assertNotNull(result.getData().get("token"));
    }

    @Test
    void staffLogin_doctor_notRegistered() {
        // given
        InnerLoginDTO dto = new InnerLoginDTO();
        dto.setStaffId("D001");
        dto.setPassword("123456");
        dto.setRole("医生");

        Doctor doctor = new Doctor();
        doctor.setDoctorNo("D001");
        doctor.setPassword(null);

        when(doctorMapper.selectDoctorByNo(anyString())).thenReturn(doctor);

        // when
        ResultVO<Map<String, String>> result = staffService.staffLogin(dto);

        // then
        assertFalse(result.isSuccess());
        assertEquals("未注册密码，请先注册", result.getMessage());
    }

    @Test
    void staffLogin_doctor_wrongPassword() {
        // given
        InnerLoginDTO dto = new InnerLoginDTO();
        dto.setStaffId("D001");
        dto.setPassword("wrong");
        dto.setRole("医生");

        Doctor doctor = new Doctor();
        doctor.setDoctorNo("D001");
        doctor.setPassword("123456");

        when(doctorMapper.selectDoctorByNo(anyString())).thenReturn(doctor);

        // when
        ResultVO<Map<String, String>> result = staffService.staffLogin(dto);

        // then
        assertFalse(result.isSuccess());
    }

    // ========== staffInfo 测试 ==========

    @Test
    void staffInfo_doctor_success() {
        // given
        Doctor doctor = new Doctor();
        doctor.setDoctorNo("D001");
        doctor.setDoctorName("张医生");
        doctor.setPhone("13800138000");
        doctor.setEmail("test@test.com");
        doctor.setSpecialty("内科");
        doctor.setTitle("主任医师");
        doctor.setRole("医生");
        doctor.setDoctorPic("http://example.com/pic.jpg");

        when(doctorMapper.selectDoctorByNo(anyString())).thenReturn(doctor);
        when(doctorMapper.getDeptName(anyString())).thenReturn("内科");

        // when
        StaffInformationVO result = staffService.staffInfo("D001", "医生");

        // then
        assertNotNull(result);
        assertEquals("D001", result.getStaffId());
        assertEquals("张医生", result.getName());
    }

    @Test
    void staffInfo_admin_success() {
        // given
        Admin admin = new Admin();
        admin.setAdminNo("A001");
        admin.setAdminName("管理员");
        admin.setPhone("13800138000");
        admin.setEmail("admin@test.com");
        admin.setRole("管理员");
        admin.setAdminPic("http://example.com/pic.jpg");

        when(adminMapper.selectAdminByNo(anyString())).thenReturn(admin);

        // when
        StaffInformationVO result = staffService.staffInfo("A001", "管理员");

        // then
        assertNotNull(result);
        assertEquals("A001", result.getStaffId());
    }

    @Test
    void staffInfo_invalidRole() {
        // then
        assertThrows(BusinessException.class, () ->
            staffService.staffInfo("D001", "非法角色")
        );
    }

    // ========== staffUpdatePsw 测试 ==========

    @Test
    void staffUpdatePsw_doctor_success() {
        // given
        UpdatePswDTO dto = new UpdatePswDTO();
        dto.setStaffId("D001");
        dto.setOldPsw("123456");
        dto.setNewPsw("654321");
        dto.setRole("医生");

        Doctor doctor = new Doctor();
        doctor.setDoctorNo("D001");
        doctor.setPassword("123456");

        when(doctorMapper.selectDoctorByNo(anyString())).thenReturn(doctor);
        when(doctorMapper.doctorUpdate(anyString(), anyString())).thenReturn(1);

        // when & then
        assertDoesNotThrow(() -> staffService.staffUpdatePsw(dto));
    }

    @Test
    void staffUpdatePsw_wrongOldPassword() {
        // given
        UpdatePswDTO dto = new UpdatePswDTO();
        dto.setStaffId("D001");
        dto.setOldPsw("wrong");
        dto.setNewPsw("654321");
        dto.setRole("医生");

        Doctor doctor = new Doctor();
        doctor.setDoctorNo("D001");
        doctor.setPassword("123456");

        when(doctorMapper.selectDoctorByNo(anyString())).thenReturn(doctor);

        // then
        assertThrows(BusinessException.class, () ->
            staffService.staffUpdatePsw(dto)
        );
    }
}
