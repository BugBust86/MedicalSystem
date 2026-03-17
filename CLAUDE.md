# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a **full-stack medical management system** with:
- **Backend**: Spring Boot + MyBatis + MySQL (port 8080)
- **Frontend**: Vue 3 + TypeScript + Vite + Element Plus

The system provides appointment booking, staff management, prescription handling, and lab technician workflows for a hospital.

## Directory Structure

```
MedicalSystem/
├── MedicalSystem/                    # Spring Boot backend
│   ├── src/main/java/lds/com/medicalsystem/
│   │   ├── MedicalSystemApplication.java   # Entry point with @MapperScan
│   │   ├── common/                        # Shared utilities
│   │   │   ├── DTO/                       # InnerLoginDTO, InnerRegisterDTO, UpdatePswDTO
│   │   │   ├── VO/                        # ResultVO, StaffInformationVO
│   │   │   ├── MVC/                       # Staff authentication (Controller, Service, Mapper)
│   │   │   └── utils/
│   │   │       ├── config/                # JWTUtil, WebConfig, LoginInterceptor, ThreadLocalUtil
│   │   │       ├── exception/             # BusinessException, GlobalExceptionHandler
│   │   │       ├── IdCardValidator.java
│   │   │       └── ValidIdCard.java
│   │   ├── staff/                         # Hospital staff management
│   │   │   ├── admin/                     # Admin operations
│   │   │   ├── doctor/                    # Doctor operations (prescriptions, patients)
│   │   │   ├── labTech/                   # Lab technician operations
│   │   │   └── VerifyUtil.java
│   │   └── user/                          # Patient management
│   ├── src/main/resources/
│   │   └── application.yml                # Database & app configuration
│   └── pom.xml
│
└── MedicalSystemFrontEnd/             # Vue 3 frontend
    ├── src/
    │   ├── api/                          # API service functions
    │   ├── views/                        # Page components (admin/, doctor/, labTech/)
    │   ├── router/                      # Vue Router configuration
    │   ├── utils/                       # Utilities (request.ts - axios wrapper)
    │   └── main.ts                      # Entry point
    ├── package.json
    └── vite.config.ts
```

## Running the Full Application

**Prerequisites**: MySQL running on localhost:3306 with `medical_db` database initialized from `medical_db.sql`

```bash
# Backend (MedicalSystem/)
cd MedicalSystem
mvn spring-boot:run

# Frontend (MedicalSystemFrontEnd/)
cd MedicalSystemFrontEnd
npm run dev
```

## Development Commands

### Backend
```bash
mvn clean package          # Build JAR
mvn spring-boot:run       # Run
mvn test                  # Run tests
mvn test -Dtest=JWTTest   # Run single test
```

### Frontend
```bash
npm run dev               # Start dev server
npm run build             # Production build
npm run lint              # Lint code
npm run format            # Format with Prettier
```

## Database Configuration

- **Database**: MySQL (`medical_db`)
- **Connection**: `jdbc:mysql://localhost:3306/medical_db`
- **Credentials**: username: `root`, password: `1qaz2wsx`
- **Initialize**: Run `medical_db.sql` to create tables and seed data

Default admin account: `admin_no: A2022000`, `password: 123456`

## Key Integration Points

- Frontend API base: `http://localhost:8080` (configurable in `.env.development`)
- JWT authentication: Token stored in localStorage, sent via `Authorization: Bearer` header
- API response format: `{ code: number, data: T, message?: string }`

## Key API Routes

| Path | Description |
|------|-------------|
| `/staff/*` | Staff login, registration |
| `/doctor/*` | Doctor personal info, prescriptions, patients |
| `/admin/*` | Manage doctors, lab techs, departments, schedules |
| `/labTech/*` | Lab technician check items |
| `/user/*` | Patient registration, login, appointments |

## Technology Stack

### Backend
- Java 17
- Spring Boot 4.0.1
- MyBatis 4.0.1
- MySQL 8.x
- JWT Authentication (java-jwt 4.4.0)
- Lombok 1.18.30
- Spring Validation
- Spring Actuator

### Frontend
- Vue 3.5.27
- TypeScript 5.9.3
- Vite 7.3.1
- Element Plus 2.13.2
- Vue Router 5.0.1
- Axios 1.13.5
- Sass, TailwindCSS

## Architecture Patterns

### Backend (MVC + MyBatis)
- **Controller**: Handle HTTP requests, return ResultVO
- **Service**: Business logic, transaction management
- **Mapper**: Database operations via MyBatis XML annotations
- **Entity**: Database table mapping
- **DTO/VO**: Data transfer objects for API

### Frontend
- **Views**: Page components
- **API**: Centralized API service functions
- **Router**: Route definitions with guards
- **Utils**: Axios wrapper with interceptors
