package lds.com.medicalsystem.common.DTO;

import lombok.Data;

@Data
public class UpdatePswDTO {
    private String oldPsw;
    private String newPsw;
    private String role;
    private String staffId;
}
