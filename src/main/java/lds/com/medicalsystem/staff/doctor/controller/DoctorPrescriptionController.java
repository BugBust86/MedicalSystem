package lds.com.medicalsystem.staff.doctor.controller;

import lds.com.medicalsystem.common.VO.ResultVO;
import lds.com.medicalsystem.staff.doctor.DTO.PrescriptionSubmitDTO;
import lds.com.medicalsystem.staff.doctor.service.DoctorPrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 管理处方、填写病历本相关操作
@RestController
@RequestMapping("/doctor/prescription")
public class DoctorPrescriptionController {
    @Autowired
    private DoctorPrescriptionService dps;
    // 插入前端传来的医生填写的处方
    @PostMapping("/submit")
    public ResultVO<Void> submit(@RequestBody PrescriptionSubmitDTO submitDTO) {
        try {
            boolean success = dps.submitPrescription(submitDTO);
            String msg = success ? "处方提交成功" : "处方提交失败";
            return ResultVO.success(msg);
        } catch (Exception e) {
            String msg1 = "提交失败：" + e.getMessage();
            return ResultVO.error(msg1);
        }
    }
    //
    //
}
