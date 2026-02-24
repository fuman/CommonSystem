# Group System Management Portal

그룹사에서 사용하는 시스템(어플리케이션/서브시스템/프로그램/메뉴/API/권한/사용자)을 통합 관리하는 포털 기본 구현입니다.

## 아키텍처
- **Frontend**: Vue 3 + DevExtreme(DevExpress Vue) 25.2 + JavaScript
- **Backend**: Spring Boot RESTful API + Spring Data JPA + H2

## 도메인 관계
- Application 1:N Subsystem
- Subsystem 1:N Program
- Program 1:N Menu
- Program 1:N API
- Menu 1:N Permission
- API 1:N Permission
- Permission N:M User (내부/외부 구분)

## 실행 방법
### Backend
```bash
cd backend
mvn spring-boot:run
```

### Frontend
```bash
cd frontend
npm install
npm run dev
```

- Frontend: http://localhost:5173
- Backend API: http://localhost:8080/api
- H2 console: http://localhost:8080/h2-console

## 주요 API
- `GET/POST /api/applications`
- `GET/POST /api/subsystems`
- `GET/POST /api/programs`
- `GET/POST /api/menus`
- `GET/POST /api/apis`
- `GET/POST /api/permissions`
- `GET/POST /api/users`
