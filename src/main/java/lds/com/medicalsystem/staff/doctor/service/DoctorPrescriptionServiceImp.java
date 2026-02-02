package lds.com.medicalsystem.staff.doctor.service;

import lds.com.medicalsystem.staff.doctor.DTO.PrescriptionSubmitDTO;
import lds.com.medicalsystem.staff.doctor.entity.Prescription;
import lds.com.medicalsystem.staff.doctor.entity.PrescriptionDetail;
import lds.com.medicalsystem.staff.doctor.mapper.DoctorPrescriptionMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class DoctorPrescriptionServiceImp implements DoctorPrescriptionService{
    @Autowired
    private DoctorPrescriptionMapper dpm;

    @Override
    public boolean submitPrescription(PrescriptionSubmitDTO submitDTO) {
        // 1. 构建处方主表对象并插入
        Prescription prescription = new Prescription();
        BeanUtils.copyProperties(submitDTO, prescription);
        int mainResult = dpm.insertPrescription(prescription);
        if (mainResult <= 0) {
            throw new RuntimeException("处方主表插入失败");
        }
        // 获取插入后生成的主键ID（需在MyBatis中配置useGeneratedKeys=true）
        String prescriptionId = prescription.getPrescriptionId();

        // 2. 构建处方详情列表并批量插入
        List<PrescriptionDetail> detailList = new ArrayList<>();
        for (PrescriptionSubmitDTO.PrescriptionDetailDTO detailDTO : submitDTO.getPrescriptionDetails()) {
            PrescriptionDetail detail = new PrescriptionDetail();
            BeanUtils.copyProperties(detailDTO, detail);
            detail.setPrescriptionId(prescriptionId); // 关联处方主表ID
            detailList.add(detail);
        }
        if (!detailList.isEmpty()) {
            int detailResult = dpm.batchInsertDetails(detailList);
            if (detailResult <= 0) {
                throw new RuntimeException("处方详情表插入失败");
            }
        }

        return true;
    }
}
