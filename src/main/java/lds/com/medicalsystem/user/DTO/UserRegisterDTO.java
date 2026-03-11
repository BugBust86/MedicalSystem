package lds.com.medicalsystem.user.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserRegisterDTO {
    @Pattern(regexp = "^\\d{11}$")
    private String phone;
    @Pattern(regexp = "^\\w{5,16}$")
    private String psw;
    @NotBlank
    private String userName;
}
