package lds.com.medicalsystem.staff.doctor.DTO;

import lombok.Data;

@Data
public class DoctorUpdateDTO {
    private String email;
    private String phone;
    private String specialty;
}
