package lds.com.medicalsystem.staff.doctor.service;

import lds.com.medicalsystem.staff.doctor.DTO.PrescriptionSubmitDTO;

public interface DoctorPrescriptionService {
    // 传入前端提交的表单，执行插入数据库操作
    boolean submitPrescription(PrescriptionSubmitDTO submitDTO);

}
