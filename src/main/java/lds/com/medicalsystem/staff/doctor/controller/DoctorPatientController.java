package lds.com.medicalsystem.staff.doctor.controller;

import lds.com.medicalsystem.common.VO.ResultVO;
import lds.com.medicalsystem.common.utils.config.ThreadLocalUtil;
import lds.com.medicalsystem.common.utils.exception.BusinessException;
import lds.com.medicalsystem.staff.VerifyUtil;
import lds.com.medicalsystem.staff.doctor.DTO.HistoryAddDTO;
import lds.com.medicalsystem.staff.doctor.VO.PatientReserveInfoVO;
import lds.com.medicalsystem.staff.doctor.service.DoctorPatientService;
import lds.com.medicalsystem.staff.doctor.utils.DoctorTokenUtil;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/doctor")
public class DoctorPatientController {
    private final DoctorPatientService doctorPatientService;
    public DoctorPatientController(DoctorPatientService doctorPatientService) {
        this.doctorPatientService = doctorPatientService;
    }

    // 根据工号查预约该医生的患者信息表
    @GetMapping("/patientReserve")
    public ResultVO<List<PatientReserveInfoVO>> patientReserveFindByDocNo(){
        String doctorNo = DoctorTokenUtil.getDoctorNo();
        return ResultVO.success(doctorPatientService.patientReserveByDocNo(doctorNo));
    }

    // 医生接诊，填写病历本，往medical_histories表写入数据
    @PostMapping("/addMedicalHistory")
    public ResultVO<Void> addMedicalHistory(@RequestBody HistoryAddDTO dto){
        VerifyUtil.doctorVerify();
        try {
            doctorPatientService.doctorWriteHistory(dto);
        } catch (Exception e) {
            throw new BusinessException("service执行异常"+e);
        }
        return ResultVO.success("填写成功");
    }

}
