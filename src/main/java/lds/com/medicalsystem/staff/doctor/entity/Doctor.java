package lds.com.medicalsystem.staff.doctor.entity;


import lombok.Data;

@Data

public class Doctor {
    private String doctorNo;
    private String doctorName;
    private String password;
    // 职责，医生
    private String role;
    // 职称，枚举类
    private DoctorTitle title;
    private String phone;
    private String email;
    // 擅长
    private String specialty;
    // 科室
    private String department;
    private String CreateTime;
    private String UpdateTime;
    // 医生头像
    private String doctorPic;
}
