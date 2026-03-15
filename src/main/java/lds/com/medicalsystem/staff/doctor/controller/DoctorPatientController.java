package lds.com.medicalsystem.staff.doctor.controller;

import lds.com.medicalsystem.common.VO.ResultVO;
import lds.com.medicalsystem.common.utils.config.ThreadLocalUtil;
import lds.com.medicalsystem.staff.VerifyUtil;
import lds.com.medicalsystem.staff.doctor.VO.PatientReserveInfoVO;
import lds.com.medicalsystem.staff.doctor.service.DoctorPatientService;
import org.springframework.web.bind.annotation.*;

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
        VerifyUtil.doctorVerify();
        Map<String,Object> map = ThreadLocalUtil.get();
        String doctorNo = (String)map.get("工号");
        return ResultVO.success(doctorPatientService.patientReserveByDocNo(doctorNo));
    }


}
