package lds.com.medicalsystem.common.DTO;

import lombok.Data;

@Data
public class UpdatePswDTO {
    private String OldPsw;
    private String newPsw;
    private String role;
    private String staffId;
}
