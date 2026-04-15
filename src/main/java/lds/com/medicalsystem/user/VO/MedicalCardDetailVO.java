package lds.com.medicalsystem.user.VO;

import lds.com.medicalsystem.user.entity.GenderType;
import lds.com.medicalsystem.user.entity.RelationType;
import lombok.Data;

// 查看就诊卡详情VO
@Data
public class MedicalCardDetailVO {
    private int cardId;
    private String patientName;
    private String idNumber;
    private GenderType gender;
    private int age;
    private RelationType relationship;
    private String contactPhone;
    private int userId;
}
