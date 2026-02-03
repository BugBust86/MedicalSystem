package lds.com.medicalsystem.staff.doctor.service;

import lds.com.medicalsystem.staff.doctor.DTO.PrescriptionAddDTO;
import lds.com.medicalsystem.staff.doctor.DTO.PrescriptionUpdateDTO;
import lds.com.medicalsystem.staff.doctor.VO.PrescriptionDetailVO;
import lds.com.medicalsystem.staff.doctor.VO.PrescriptionVO;
import lds.com.medicalsystem.staff.doctor.entity.Prescription;
import lds.com.medicalsystem.staff.doctor.entity.PrescriptionDetail;
import lds.com.medicalsystem.staff.doctor.mapper.DoctorPrescriptionMapper;
import lds.com.medicalsystem.staff.doctor.utils.DoctorTokenUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class DoctorPrescriptionServiceImp implements DoctorPrescriptionService{
    @Autowired
    private DoctorPrescriptionMapper dpm;

    @Override
    public void addPrescription(PrescriptionAddDTO addDTO) {
        // 1. 构建处方主表对象并插入
        Prescription prescription = new Prescription();
        BeanUtils.copyProperties(addDTO, prescription);
        int mainResult = dpm.insertPrescription(prescription);
        if (mainResult <= 0) {
            throw new RuntimeException("处方主表插入失败");
        }
        // 获取插入后生成的主键ID（需在MyBatis中配置useGeneratedKeys=true）
        int prescriptionId = prescription.getPrescriptionId();

        // 2. 构建处方详情列表并批量插入
        List<PrescriptionDetail> detailList = new ArrayList<>();
        // 将前端传入的详情列表 for循环 复制到 与数据库对应的实体类中
        for (PrescriptionAddDTO.PrescriptionDetailDTO detailDTO : addDTO.getPrescriptionDetails()) {
            PrescriptionDetail detail = new PrescriptionDetail();
            BeanUtils.copyProperties(detailDTO, detail);
            detail.setPrescriptionId(prescriptionId); // 关联处方主表ID
            detailList.add(detail);
        }
        // 若列表不为空则执行批量插入操作
        if (!detailList.isEmpty()) {
            int detailResult = dpm.batchInsertDetails(detailList);
            if (detailResult <= 0) {
                throw new RuntimeException("处方详情表插入失败");
            }
        }
    }
    /**
     * 修改处方（主表+详情表）
     */
    public void updatePrescription(PrescriptionUpdateDTO updateDTO) {
        // 1. 校验处方是否存在
        Prescription existPrescription = dpm.getById(updateDTO.getPrescriptionId());
        if (existPrescription == null) {
            throw new RuntimeException("处方不存在，无法修改");
        }

        // 2. 修改主表（只更新非空字段）
        Prescription prescription = new Prescription();
        BeanUtils.copyProperties(updateDTO, prescription);
        int mainResult = dpm.updatePrescription(prescription);
        if (mainResult <= 0) {
            throw new RuntimeException("处方修改失败");
        }

        // 3. 处理详情：新增/修改/删除
        // 遍历PrescriptionUpdateDTO中的DetailUpdateDTO列表，
        // detailDTO是变量名，PrescriptionUpdateDTO.DetailUpdateDTO是变量类型，updateDTO.getDetailList()是列表名
        for (PrescriptionUpdateDTO.DetailUpdateDTO detailDTO : updateDTO.getDetailList()) {
            if ("ADD".equals(detailDTO.getOperateType())) {
                // 新增详情
                PrescriptionDetail detail = new PrescriptionDetail();
                BeanUtils.copyProperties(detailDTO, detail);
                detail.setPrescriptionId(updateDTO.getPrescriptionId());
                dpm.batchInsertDetails(List.of(detail));
            } else if ("UPDATE".equals(detailDTO.getOperateType())) {
                // 修改详情
                if (detailDTO.getDetailId() == null) {
                    throw new RuntimeException("修改详情时，详情ID不能为空");
                }
                PrescriptionDetail detail = new PrescriptionDetail();
                BeanUtils.copyProperties(detailDTO, detail);
                dpm.updateDetail(detail);
            } else if ("DELETE".equals(detailDTO.getOperateType())) {
                // 删除详情
                if (detailDTO.getDetailId() == null) {
                    throw new RuntimeException("删除详情时，详情ID不能为空");
                }
                dpm.deleteDetail(detailDTO.getDetailId());
            } else {
                throw new RuntimeException("不支持的操作类型：" + detailDTO.getOperateType());
            }
        }
    }

    /**
     * 删除处方（物理删除，级联删除详情）
     */
    public void deletePrescription(Integer prescriptionId) {
        // 1. 校验处方是否存在
        Prescription existPrescription = dpm.getById(prescriptionId);
        if (existPrescription == null) {
            throw new RuntimeException("处方不存在，无法删除");
        }
        // 2. 级联删除详情
        dpm.deleteDetailByPrescriptionId(prescriptionId);
        // 3. 删除主表
        int mainResult = dpm.deletePrescription(prescriptionId);
        if (mainResult <= 0) {
            throw new RuntimeException("处方删除失败");
        }
    }
    @Override
    //根据ID查询处方（含详情）
    public PrescriptionDetailVO getPrescriptionById(Integer prescriptionId) {
        // 创建一个对象存储
        PrescriptionDetailVO pDetailVO = new PrescriptionDetailVO();
        // 1. 查询主表
        Prescription prescription = dpm.getById(prescriptionId);
        if (prescription == null) {
            throw new RuntimeException("处方不存在");
        }
        pDetailVO.setPrescriptionName(prescription.getPrescriptionName());
        pDetailVO.setPrescriptionDesc(prescription.getPrescriptionDesc());
        pDetailVO.setDisease(prescription.getDisease());
        // 2. 查询详情
        pDetailVO.setPrescriptionDetails(dpm.listByPrescriptionId(prescriptionId));
        // 3. 组装返回结果
        return pDetailVO;
    }
    @Override   // 获取处方名+id列表
    public List<PrescriptionVO> getPNameList() {
        try {
            String doctorNo = DoctorTokenUtil.getDoctorNo();
            return dpm.getPNameListByDocNo(doctorNo);
        } catch (Exception e) {
            throw new RuntimeException("获取列表失败",e);
        }
    }
}
