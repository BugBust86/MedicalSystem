package lds.com.medicalsystem.staff.doctor.controller;

import lds.com.medicalsystem.common.VO.ResultVO;
import lds.com.medicalsystem.staff.doctor.DTO.PrescriptionAddDTO;
import lds.com.medicalsystem.staff.doctor.DTO.PrescriptionUpdateDTO;
import lds.com.medicalsystem.staff.doctor.VO.PrescriptionDetailVO;
import lds.com.medicalsystem.staff.doctor.VO.PrescriptionVO;
import lds.com.medicalsystem.staff.doctor.service.DoctorPrescriptionService;
import lds.com.medicalsystem.staff.doctor.utils.DoctorTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// 管理处方、填写病历本相关操作
@RestController
@RequestMapping("/doctor/prescription")
public class DoctorPrescriptionController {
    @Autowired
    private DoctorPrescriptionService dps;
    // 插入前端传来的医生填写的处方
    @PostMapping("/add")
    public ResultVO<Void> submitInsert(@Validated @RequestBody PrescriptionAddDTO addDTO) {
        // 将医生工号通过账号登录的Token提取值赋给addDTO中的DoctorId
        addDTO.setDoctorId(DoctorTokenUtil.getDoctorNo());
        dps.addPrescription(addDTO);
        return ResultVO.success("新增处方成功");
    }
    // 对前端传来的医生填写的处方部分修改（医生工号不修改）
    @PatchMapping("/update")
    public ResultVO<Void> submitUpdate(@Validated @RequestBody PrescriptionUpdateDTO updateDTO) {
        updateDTO.setDoctorId(DoctorTokenUtil.getDoctorNo());
        dps.updatePrescription(updateDTO);
        return ResultVO.success("修改处方成功");
    }
    // 删除处方
    @DeleteMapping("/delete/{prescriptionId}")
    public ResultVO<Void> deletePrescription( @PathVariable("prescriptionId") Integer prescriptionId) {
        dps.deletePrescription(prescriptionId);
        return ResultVO.success("删除处方成功");
    }
    // 根据医生工号显示全部处方名+处方id列表
    @GetMapping("/query/all")
    public ResultVO<List<PrescriptionVO>> getPrescription() {
        List<PrescriptionVO> pNameList = dps.getPNameList();
        return ResultVO.success(pNameList);
    }
    // 根据处方id查询处方详细信息,即点击对应的处方名显示处方
    @GetMapping("query/detail")
    public ResultVO<PrescriptionDetailVO> getPrescriptionList(Integer prescriptionId) {
        PrescriptionDetailVO prescription = dps.getPrescriptionById(prescriptionId);
        return ResultVO.success(prescription);
    }
}
