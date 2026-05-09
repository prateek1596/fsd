# TeamTracker (Scaffold)

This archive contains a minimal scaffold for a Team Collaboration & Task Tracking app:
- Backend: Spring Boot (Java 17), MySQL, JPA
- Frontend: React + Vite, drag-and-drop Kanban (react-beautiful-dnd)
- Docker Compose to run MySQL + Backend

## Run (dev)
1. Start backend + DB:
   docker-compose up --build
2. Build backend jar (optional) from backend/:
   mvn clean package -DskipTests
3. Start frontend:
   cd frontend
   npm install
   npm run dev

API root: http://localhost:8080/api

