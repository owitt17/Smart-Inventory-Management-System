# Smart-Inventory-Management-System
Project Overview
A full-stack web-based Inventory Management System that will allow user to manage their stock items.
Include authentication, role-based access, dashboard analytics, and image uploads. The system has Admin and User roles with allowed permissions.

Backend:
- Java 21
- Spring Boot 4
- Spring Security (JWT Authentication)
- Spring Data JPA (Hibernate)
- MySQL Database
- Maven
- Lombok

Frontend:
- React (Typescript)
- Axios
- React Router
- Modern CSS (Responsive UI, Card Layout, Modals)

Security:
- JWT Token-based Authentication
- Role-Based Access Control (ADMIN, USER)
- CORS Configuration
- Password Encryption (BCrypt)

Fucntionality:
- Add, Edit, Delete items (ADMIN)
- View and search items (ADMIN & USER)
- Quantity tracking
- Stock status
- Image upload
- Dashboard

Notes: I use Agile sprint-based development where I keep track of what I need to complete from day to day. In this short development it took me around 5 days to complete since few sprints I pushed into the same day.
How to run:
Backend:
cd backend
./mvnw spring-boot:run 

Frontend:
cd frontend
npm install
npm start

Created by Owitt Juada (11 January 2026 - 16 January 2026)
