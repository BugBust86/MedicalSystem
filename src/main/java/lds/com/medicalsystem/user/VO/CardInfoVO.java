package lds.com.medicalsystem.user.VO;

import lds.com.medicalsystem.user.entity.RelationType;
import lombok.Data;

@Data
public class CardInfoVO {
    private int cardId;
    private String patientName;
    private RelationType relationship;
}
