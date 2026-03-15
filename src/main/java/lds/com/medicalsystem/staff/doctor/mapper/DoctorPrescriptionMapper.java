package lds.com.medicalsystem.staff.doctor.mapper;

import lds.com.medicalsystem.staff.doctor.VO.PrescriptionVO;
import lds.com.medicalsystem.staff.doctor.entity.Prescription;
import lds.com.medicalsystem.staff.doctor.entity.PrescriptionDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface DoctorPrescriptionMapper {
    // 插入处方主表，返回受影响的行数
    @Insert("INSERT INTO prescription (disease, prescription_name, prescription_desc, doctor_no) " +
            "VALUES (#{disease}, #{prescriptionName}, #{prescriptionDesc}, #{doctorNo})")
    @Options(useGeneratedKeys = true, keyProperty = "prescriptionId", keyColumn = "prescription_id")
    int insertPrescription(Prescription prescription);
    // 批量插入处方详情
    int batchInsertDetails(@Param("details") List<PrescriptionDetail> details);

    // 修改处方主表,不修改id主键，仅修改处方描述、处方名
    @Update("update prescription set disease=#{disease},prescription_desc = #{prescriptionDesc}," +
            "prescription_name = #{prescriptionName} where doctor_no = #{doctorNo};")
    int updatePrescription(Prescription prescription);
    // 修改详情
    @Update("UPDATE prescription_detail SET " +
            "drug_name = #{drugName}, " +
            "dosage = #{dosage}, " +
            "frequency = #{frequency}, " +
            "`usage` = #{usage}, " +
            "drug_remark = #{drugRemark} " +
            "WHERE detail_id = #{detailId}")
    int updateDetail(PrescriptionDetail detail);

    // 删除处方
    @Delete("DELETE FROM prescription WHERE prescription_id = #{prescriptionId}")
    int deletePrescription(int prescriptionId);
    // 删除详情
    @Delete("DELETE FROM prescription_detail WHERE detail_id = #{detailId}")
    int deleteDetail(int detailId);
    // 批量删除处方下的所有详情（删除处方时级联删除）
    @Delete("DELETE FROM prescription_detail WHERE prescription_id = #{prescriptionId}")
    int deleteDetailByPrescriptionId(int prescriptionId);

    // 根据ID查询处方
    @Select("SELECT * FROM prescription WHERE prescription_id = #{prescriptionId}")
    Prescription getById(int prescriptionId);
    // 根据处方ID查询详情
    @Select("SELECT * FROM prescription_detail WHERE prescription_id = #{prescriptionId}")
    List<PrescriptionDetail> listByPrescriptionId(int prescriptionId);
    // 根据处方表中的医生工号查该医生管理的所有处方(处方名+处方id）
    List<PrescriptionVO> getPNameListByDocNo(String doctorNo);
    // 根据医生工号查所有处方的名字和id
    @Select("select prescription_id,prescription_name from prescription where doctor_no = #{doctorNo};")
    List<PrescriptionVO> getPrescriptionListByDocNo(String doctorNo);
}
