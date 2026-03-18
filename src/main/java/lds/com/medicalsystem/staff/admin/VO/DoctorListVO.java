package lds.com.medicalsystem.staff.admin.VO;

import lds.com.medicalsystem.staff.doctor.entity.DoctorTitle;
import lombok.Data;

@Data
public class DoctorListVO {
    private String doctorNo;
    private String doctorName;
    private String deptName;
    private DoctorTitle title;
    private String phone;
    private String email;
    private String specialty;
}
