# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a **Spring Boot + MyBatis** medical management system providing appointment booking, staff management, and prescription handling for a hospital.

## Build & Run Commands

```bash
# Build the project
mvn clean package

# Run the application
mvn spring-boot:run

# Run tests
mvn test

# Run a single test class
mvn test -Dtest=JWTTest

# Run a specific test method
mvn test -Dtest=JWTTest#testGenerateToken
```

## Database Configuration

- **Database**: MySQL (`medical_db`)
- **Connection**: `jdbc:mysql://localhost:3306/medical_db`
- **Credentials**: username: `root`, password: `123456`
- **Initialize**: Run `medical_db.sql` to create tables and seed data

Default admin account: `admin_no: A2022000`, `password: 123456`

## Architecture

```
src/main/java/lds/com/medicalsystem/
├── MedicalSystemApplication.java     # Entry point with @MapperScan
├── common/                           # Shared utilities
│   ├── MVC/                         # Staff authentication (Controller, Service, Mapper)
│   ├── DTO/                         # InnerLoginDTO, InnerRegisterDTO
│   ├── VO/                          # ResultVO, StaffInformationVO
│   └── utils/
│       ├── config/                  # JWTUtil, WebConfig, LoginInterceptor, ThreadLocalUtil
│       ├── exception/               # BusinessException, GlobalExceptionHandler
│       ├── IdCardValidator.java
│       └── ValidIdCard.java
├── staff/                           # Hospital staff management
│   ├── admin/                       # Admin operations
│   │   ├── controller/              # AdminManageStaff, AdminManageDept, AdminArrange
│   │   ├── service/                 # AdminStaffService, AdminDeptService, AdminArrangeService
│   │   ├── mapper/                  # AdminMapper, AdminDeptMapper, AdminArrangeMapper
│   │   ├── entity/                  # Admin, WorkTimeType
│   │   ├── DTO/                     # AdminRegisterDoctorDTO, WorkTableInsertDTO, etc.
│   │   ├── VO/                      # DoctorListVO, WorkListVO, PageResultVO
│   │   └── VerifyUtil.java
│   ├── doctor/                      # Doctor operations
│   │   ├── controller/              # DoctorPersonal, DoctorPrescription
│   │   ├── service/                 # DoctorPersonalService, DoctorPrescriptionService
│   │   ├── mapper/                  # DoctorMapper, DoctorPersonalMapper, DoctorPrescriptionMapper
│   │   ├── entity/                  # Doctor, Prescription, PrescriptionDetail, DoctorTitle
│   │   └── DTO/VO/                   # PatientInfo, Prescription, InfoDTO
│   └── labTech/                     # Lab technician operations
│       ├── controller/              # LabTechController
│       ├── service/                 # LabTechService
│       ├── mapper/                  # LabTechMapper
│       ├── entity/                  # LabTech, CheckItem
│       └── vo/                      # LabItemSampleVO
└── user/                            # Patient/user management
    ├── controller/                  # UserRegisterLogin, UserReserve, UserAppiontment
    ├── service/                      # UserService, UserReserveService
    ├── mapper/                       # UserMapper, UserReserveMapper
    ├── entity/                       # User, MedicalCard, MedicalRecord, MedicalHistory
    ├── DTO/                          # UserLoginDTO, UserRegisterDTO, UserReserveDTO
    └── VO/                          # UserInfoVO
```

## Key API Routes

| Path | Description |
|------|-------------|
| `/staff/*` | Staff login, registration |
| `/doctor/*` | Doctor personal info, prescriptions |
| `/admin/*` | Manage doctors, lab techs, departments, schedules |
| `/labTech/*` | Lab technician check items |
| `/user/*` | Patient registration, login, appointments |

## Technology Stack

- Java 17
- Spring Boot 4.0.1
- MyBatis 4.0.1
- MySQL 8.x
- JWT Authentication (java-jwt)
- Lombok
- Spring Validation
