package lds.com.medicalsystem.staff.doctor.mapper;

import lds.com.medicalsystem.staff.doctor.entity.Prescription;
import lds.com.medicalsystem.staff.doctor.entity.PrescriptionDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DoctorPrescriptionMapper {
    // 插入处方主表，返回自增主键
    @Insert("INSERT INTO prescription (prescription_name, prescription_desc, doctor_no) " +
            "VALUES (#{prescriptionName}, #{prescriptionDesc}, #{doctorNo})")
    @Options(useGeneratedKeys = true, keyProperty = "prescriptionId", keyColumn = "prescriptionId")
    int insertPrescription(Prescription prescription);

    // 批量插入处方详情
    int batchInsertDetails(@Param("details") List<PrescriptionDetail> details);
}
