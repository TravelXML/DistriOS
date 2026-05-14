# DistriCore API - Quick Start Guide

## 🎯 Overview

DistriCore is a complete REST API for distribution management. This guide helps you get it running quickly.

## 📋 Prerequisites

- **Java 21** (or later)
- **Maven 3.6+** (for building only)
- **Docker** (optional, for PostgreSQL/Redis)

## 🚀 Quick Start Options

### Option 1: Run with Docker Compose (Recommended)

**Best for**: Complete setup with all services included.

```bash
cd /home/sap-ai-plug/CODE/DistriOS

# Fix Docker permissions if needed
sudo usermod -aG docker $USER && newgrp docker

# Start services
docker compose up -d

# Wait for services to be ready (check with: docker compose ps)

# Run the app
java -jar target/districore-api-0.1.0.jar
```

**Access points:**
- API: http://localhost:8080/api/v1
- Swagger UI: http://localhost:8080/swagger-ui/index.html
- H2 Console: http://localhost:8080/h2-console (if using H2)

---

### Option 2: Run with In-Memory H2 Database (No Docker)

**Best for**: Quick testing without external dependencies.

```bash
cd /home/sap-ai-plug/CODE/DistriOS

# Run with H2 in-memory database
java -jar target/districore-api-0.1.0.jar --spring.profiles.active=test
```

**Features:**
- ✅ No Docker required
- ✅ Instant startup
- ✅ Data persists in memory during session
- ⚠️ Data is lost when app stops

**Access:**
- API: http://localhost:8080/api/v1
- Swagger UI: http://localhost:8080/swagger-ui/index.html
- H2 Console: http://localhost:8080/h2-console

---

### Option 3: Run with External PostgreSQL/Redis (Advanced)

**Best for**: Production-like setup with persistent data.

```bash
# Start PostgreSQL manually
docker run -d --name dist-postgres \
  -e POSTGRES_DB=districore \
  -e POSTGRES_USER=districore \
  -e POSTGRES_PASSWORD=districore \
  -p 5432:5432 \
  postgres:15

# Start Redis manually
docker run -d --name dist-redis -p 6379:6379 redis:7

# Wait for services to be ready, then run:
java -jar target/districore-api-0.1.0.jar
```

---

## 🔐 Default Credentials

- **Username**: `admin`
- **Password**: `Admin123!`
- **Tenant ID**: `default`

## 📡 Testing the API

### 1. Login and Get Token

```bash
curl -X POST http://localhost:8080/api/v1/auth/login \
  -H 'Content-Type: application/json' \
  -H 'X-Tenant-Id: default' \
  -d '{
    "username": "admin",
    "password": "Admin123!"
  }'
```

Response will include a JWT token. Copy it for authenticated requests.

### 2. Get Products

```bash
curl -X GET http://localhost:8080/api/v1/products \
  -H 'Authorization: Bearer YOUR_TOKEN_HERE' \
  -H 'X-Tenant-Id: default'
```

### 3. Create an Order

```bash
curl -X POST http://localhost:8080/api/v1/orders \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer YOUR_TOKEN_HERE' \
  -H 'X-Tenant-Id: default' \
  -d '{
    "retailerId": "some-retailer-id",
    "distributorId": "some-distributor-id",
    "lineItems": [
      {
        "productId": "product-uuid",
        "quantity": 10,
        "unitPrice": 100.00
      }
    ]
  }'
```

## 🛑 Stopping Services

### Stop Everything

```bash
# If using Docker Compose
docker compose down

# Remove volumes (to delete data)
docker compose down -v
```

### View Logs

```bash
# All services
docker compose logs -f

# Just the app
docker compose logs -f app

# Just database
docker compose logs -f db
```

---

## 🔧 Troubleshooting

### "Connection refused" at port 5432/6379

**Solution**: Ensure Docker services are running
```bash
docker compose ps
docker compose up -d  # If services are not running
```

### "Access denied" Docker error

**Solution**: Add user to docker group
```bash
sudo usermod -aG docker $USER
newgrp docker
docker compose ps  # Test
```

### Application won't start

**Check logs:**
```bash
# If running locally
tail -f nohup.out

# If using Docker
docker compose logs app
```

### "Unsupported Database" error

This is fixed! We updated Flyway to version 9.22.3 which supports PostgreSQL 15.

---

## 📊 Application Architecture

```
DistriCore API
├── common          → Shared utilities, base entities
├── security        → Auth, JWT, RBAC
├── tenant          → Multi-tenancy, company config
├── distributor     → Distributor management
├── retailer        → Retailer management
├── product         → Product catalog
├── pricing         → Pricing engine
├── inventory       → Stock management
├── order           → Order processing
├── invoice         → Invoice generation
├── scheme          → Promotion engine
├── claim           → Claims processing
├── analytics       → Business analytics
├── ai              → AI insights (mock)
├── notification    → Email, SMS, WhatsApp
└── [20+ more modules]
```

---

## 📚 API Documentation

Once running, visit:
- **Swagger UI**: http://localhost:8080/swagger-ui/index.html
- **API Docs**: http://localhost:8080/v3/api-docs

All endpoints are documented with request/response examples.

---

## 🌍 Supported Industry Verticals

- **FMCG** - Fast-moving consumer goods
- **CPG** - Consumer packaged goods
- **FMCD** - Fast-moving consumer durables
- **OTC Pharma** - Over-the-counter pharmaceuticals
- **General Distribution** - Any distribution business

---

## 📞 Common Commands

```bash
# Build the project
mvn clean package -DskipTests

# Run tests
mvn test

# Skip tests during build
mvn clean package -DskipTests

# View Docker Compose status
docker compose ps

# View application logs
docker compose logs -f app

# Enter PostgreSQL database
docker compose exec db psql -U districore -d districore

# Rebuild everything
docker compose down -v && docker compose up --build
```

---

## ⚠️ Important Notes

1. **Default Database**: Uses PostgreSQL 15 (included in docker-compose.yml)
2. **JWT Secret**: Change `jwt.secret` in `application.yml` for production
3. **CORS**: Currently allows all origins (`*`), restrict in production
4. **Redis**: Used for caching and sessions (included in docker-compose.yml)

---

## 🎓 Next Steps

1. **Explore APIs**: Use Swagger UI to understand endpoints
2. **Set Up Postman**: Import OpenAPI spec for testing
3. **Study Business Rules**: See README.md for business logic
4. **Customize**: Modify `application.yml` for your needs
5. **Deploy**: Use Dockerfile for containerized deployment

---

**Happy coding with DistriCore! 🚀**