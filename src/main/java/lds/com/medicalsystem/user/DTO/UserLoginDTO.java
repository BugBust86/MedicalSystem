package lds.com.medicalsystem.user.DTO;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserLoginDTO {
    @Pattern(regexp = "^\\d{11}$")
    String phone;
    @Pattern(regexp = "^\\w{5,16}$")
    String psw;
}
