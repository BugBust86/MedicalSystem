package lds.com.medicalsystem.staff.doctor.service;

import lds.com.medicalsystem.staff.doctor.DTO.PrescriptionAddDTO;
import lds.com.medicalsystem.staff.doctor.DTO.PrescriptionUpdateDTO;
import lds.com.medicalsystem.staff.doctor.VO.PrescriptionDetailVO;
import lds.com.medicalsystem.staff.doctor.VO.PrescriptionVO;

import java.util.List;

public interface DoctorPrescriptionService {
    // 传入前端提交的表单，执行插入数据库操作
    void addPrescription(PrescriptionAddDTO addDTO);

    void updatePrescription(PrescriptionUpdateDTO updateDTO);

    void deletePrescription(Integer prescriptionId);
    //根据ID查询处方（含详情）
    PrescriptionDetailVO getPrescriptionById(Integer prescriptionId);
    // 根据医生工号显示全部 处方 列表
    List<PrescriptionVO> getPNameList();
}
