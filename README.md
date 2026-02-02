# RouteOptima - Logistics Optimization System

RouteOptima is a comprehensive logistics dashboard and optimization engine designed to calculate the most efficient shipping routes using Dijkstra's Algorithm.

## 🚀 Features

- **Optimal Routing**: Calculate shortest paths based on distance, cost, and time using Dijkstra's Algorithm.
- **Port Management**: Manage a global network of ports and warehouses.
- **Shipment Tracking**: Track active shipments and visualize their status.
- **Interactive Map**: Real-time visualization of ports and routes using OpenStreetMap (Leaflet).
- **Analytics Dashboard**: key performance indicators for logistics operations.

## 🛠️ Tech Stack

- **Backend**: Java 17, Spring Boot 3, Spring Data JPA
- **Database**: PostgreSQL 16
- **Frontend**: React 18, Vite, Leaflet.js, Chart.js
- **DevOps**: Docker, Docker Compose

## 🏗️ Architecture

- **Microservices-style Monolith**: Modular service design ready for splitting.
- **Dijkstra Service**: Core engine for graph processing.
- **REST API**: Fully documented endpoints via Swagger.

## ⚙️ Setup & Deployment

### Prerequisities
- Docker & Docker Compose
- Java 17 & Node.js 20 (for local dev without Docker)

### Run with Docker (Recommended)
1. Clone the repository.
2. Run:
   ```bash
   docker-compose up --build
   ```
3. Access the application:
   - **Frontend**: http://localhost:3000
   - **Backend API**: http://localhost:8080
   - **Swagger Docs**: http://localhost:8080/swagger-ui.html

### Manual Setup
**Backend:**
1. Navigate to `backend/`.
2. Configure `application.properties` for your local Postgres.
3. Run `mvn spring-boot:run`.

**Frontend:**
1. Navigate to `frontend/`.
2. Run `npm install` and `npm run dev`.

## 🧪 API Usage

**Find Shortest Path:**
`GET /api/optimize/shortest-path?sourceId=1&destId=4`

**Get Ports:**
`GET /api/ports`
