# DistriCore API

DistriCore is an API-first distribution management platform designed for modern distribution businesses across FMCG, CPG, FMCD, OTC Pharma, and general distribution.

## Project Overview

- Java 21, Spring Boot, Spring Data JPA, Spring Security, JWT
- PostgreSQL, Redis, Flyway, Springdoc OpenAPI
- Modular monolith architecture with clean package separation
- Multi-tenancy support via `X-Tenant-Id`
- Standardized API response wrapper and global error handling
- Seed data for company, roles, admin user, distributors, retailers, products, inventory, pricing, and schemes

## Architecture

### Modular Monolith Design

The application follows a modular monolith architecture with clean separation of concerns:

- **common**: Shared utilities, entities, DTOs, exception handling
- **security**: Authentication, authorization, JWT, RBAC
- **tenant**: Multi-tenancy management, industry configurations
- **distributor**: Distributor onboarding, management, credit limits
- **retailer**: Retailer management, KYC, credit limits
- **product**: Product catalog, categories, brands, UOM, HSN codes
- **pricing**: Price lists, pricing engine, calculations
- **inventory**: Warehouse management, stock tracking, transactions
- **order**: Order lifecycle, status management, history
- **invoice**: Invoice generation, tax calculations
- **scheme**: Scheme engine, discounts, promotions
- **claim**: Claims management, approvals
- **warranty**: Warranty tracking (FMCD)
- **recall**: Product recalls (OTC Pharma)
- **loyalty**: Loyalty programs, points management
- **complaint**: Complaint management, resolution
- **ai**: AI-powered insights (mock implementations)
- **analytics**: Business analytics and reporting
- **integration**: ERP, payment, webhook integrations
- **notification**: Email, SMS, WhatsApp notifications
- **retailerapp**: Retailer mobile app APIs
- **sfa**: Sales force automation APIs

## Industry Vertical Support

### FMCG/CPG
- SKU-based products
- Batch/expiry optional
- Schemes and promotions
- Primary/secondary sales tracking

### FMCD
- Serial number tracking
- Warranty management
- High-value inventory
- Replacement workflows

### OTC Pharma
- Mandatory batch/expiry
- HSN/GST compliance
- Drug license management
- Regulatory compliance

### General Distribution
- Configurable product attributes
- Flexible inventory rules
- Custom workflows

## Run Locally

### Prerequisites
- Java 21
- Maven 3.6+
- PostgreSQL 15+
- Redis 7+

### Setup Steps

1. **Clone and build:**
```bash
git clone <repository>
cd DistriOS
mvn clean package
```

2. **Start dependencies:**
```bash
# Using Docker Compose
docker compose up -d

# Or start PostgreSQL and Redis manually
```

3. **Configure environment:**
```bash
# Update application.yml or set environment variables
DB_HOST=localhost
DB_PORT=5432
DB_NAME=districore
DB_USER=districore
DB_PASSWORD=districore
REDIS_HOST=localhost
REDIS_PORT=6379
JWT_SECRET=your-secret-key
```

4. **Run the application:**
```bash
java -jar target/districore-api-0.1.0.jar
```

## Run with Docker Compose

```bash
docker compose up --build
```

The app will be available at `http://localhost:8080`.

## API Documentation

- **Swagger UI**: `http://localhost:8080/swagger-ui/index.html`
- **OpenAPI Spec**: `http://localhost:8080/v3/api-docs`

## Default Credentials

- **Username**: `admin`
- **Password**: `Admin123!`
- **Tenant ID**: `default`

## Sample API Calls

### Authentication
```bash
# Login
curl -X POST http://localhost:8080/api/v1/auth/login \
  -H 'Content-Type: application/json' \
  -H 'X-Tenant-Id: default' \
  -d '{"username":"admin","password":"Admin123!"}'
```

### Products
```bash
# Get products
curl -X GET http://localhost:8080/api/v1/products \
  -H 'Authorization: Bearer <token>' \
  -H 'X-Tenant-Id: default'

# Create product
curl -X POST http://localhost:8080/api/v1/products \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer <token>' \
  -H 'X-Tenant-Id: default' \
  -d '{
    "skuCode": "TEST-001",
    "productName": "Test Product",
    "brandId": "brand-uuid",
    "categoryId": "category-uuid",
    "uomId": "uom-uuid",
    "mrp": 100.00,
    "basePrice": 80.00
  }'
```

### Orders
```bash
# Create order
curl -X POST http://localhost:8080/api/v1/orders \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer <token>' \
  -H 'X-Tenant-Id: default' \
  -d '{
    "retailerId": "retailer-uuid",
    "distributorId": "distributor-uuid",
    "lineItems": [
      {
        "productId": "product-uuid",
        "quantity": 10,
        "unitPrice": 80.00
      }
    ]
  }'
```

### Claims
```bash
# Submit claim
curl -X POST http://localhost:8080/api/v1/claims \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer <token>' \
  -H 'X-Tenant-Id: default' \
  -d '{
    "type": "DAMAGE",
    "orderId": "order-uuid",
    "reason": "Product damaged during transit"
  }'
```

## Business Rules

### Multi-tenancy
- All data is tenant-scoped via `X-Tenant-Id` header
- Prevents cross-tenant data access
- Tenant context managed per request

### Security
- JWT-based authentication
- Role-based access control (RBAC)
- Password hashing with BCrypt
- Method-level security

### Inventory Management
- Real-time stock tracking
- Transaction ledger for all movements
- Support for batch, expiry, serial numbers
- Low stock and expiry alerts

### Order Flow
1. Order creation with validation
2. Price calculation and scheme application
3. Inventory reservation
4. Order confirmation and dispatch
5. Delivery and status tracking

### Pricing Engine
- Base price + distributor/retailer overrides
- Tax calculations (GST/CGST/SGST/IGST)
- Effective pricing with date ranges

### Scheme Engine
- Percentage and flat discounts
- Buy X Get Y promotions
- Quantity-based slabs
- Category/product specific schemes

## Database Schema

The application uses Flyway for database migrations:

- `V1__initial_schema.sql`: Core tables and relationships
- `V2__seed_master_data.sql`: Sample data for testing

### Key Tables
- `companies`: Tenant/company information
- `users`: User accounts and authentication
- `products`: Product catalog
- `orders`: Order management
- `inventory_transactions`: Stock movements
- `schemes`: Promotion configurations
- `claims`: Claim processing

## Testing

Run tests with:
```bash
mvn test
```

Includes:
- Unit tests for services
- Integration tests for controllers
- Authentication and authorization tests
- Business logic validation

## Future Enhancements

### Microservices Migration
The modular design allows easy extraction into microservices:
- Product Service
- Order Service
- Inventory Service
- User Service
- Analytics Service

### Advanced Features
- Event-driven architecture with Kafka
- Outbox pattern for reliability
- OpenTelemetry for observability
- Elasticsearch for search
- AI/ML integrations
- Mobile app development
- ERP integrations
- Payment gateway integrations

## Contributing

1. Follow the modular architecture
2. Write tests for new features
3. Update API documentation
4. Maintain backward compatibility
5. Use consistent error handling

## License

[License information]
  -H 'X-Tenant-Id: default' \
  -d '{"skuCode":"NEW-001","productName":"New Product","brandId":"<brandId>","categoryId":"<categoryId>","uomId":"<uomId>","packSize":1,"packSizeUom":"PCS","caseSize":10,"barcode":"9876543210123","hsnCodeId":"<hsnId>","gstRate":18.00,"mrp":100.00,"basePrice":80.00,"batchRequired":false,"expiryRequired":false,"serialNumberRequired":false,"warrantyRequired":false,"composition":"Sample","manufacturerName":"Vendor","shelfLifeDays":365,"warrantyMonths":0,"regulatoryCategory":"General"}'
```

Create order:
```bash
curl -X POST http://localhost:8080/api/v1/orders \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer <token>' \
  -H 'X-Tenant-Id: default' \
  -d '{"retailerId":"<retailerId>","distributorId":"<distributorId>","items":[{"productId":"<productId>","quantity":2,"unitPrice":90.00}],"source":"ADMIN","idempotencyKey":"order-1001"}'
```

## What is Fully Implemented

- Core multi-tenant domain design
- Auth and RBAC foundations
- Industry config CRUD
- Distributor and retailer lifecycle APIs
- Product master, categories, brands, UOMs, HSN codes
- Pricing and invoice generation
- Inventory transaction capture and stock services
- Order flow and status history
- Claims, warranty, product recalls, and complaints
- Loyalty account management
- Seeded flyway migration and sample data
- Docker Compose support
- Swagger UI support

## Structurally Ready as Placeholder

- AI adapter APIs and analytics event systems
- ERP/tax/payment integration hooks
- SFA/retailer-app dedicated front-end endpoints
- Advanced scheme engine and pricing overrides
- Full attachment/file storage
- Detailed audit/user activity trails

## Next Development Steps

1. Add business-layer permissions and RBAC enforcement.
2. Implement `company` and `tenant` onboarding flows.
3. Add batch/expiry inventory and near-expiry/low-stock alert calculations.
4. Add product recall impact and stock movement reconciliation.
5. Build integration adapters for ERP and tax validation.
6. Add comprehensive JUnit + Testcontainers coverage for key APIs.

# How to Run
Local Development
## 1. Build the project
```bash
mvn clean package
```

## 2. Start dependencies using Docker
```bash
docker compose up -d
```

## 3. Check Docker services
```bash
docker compose ps
```

## 4. Run the application
```bash
java -jar target/districore-api-0.1.0.jar
```

Run with Docker Compose
```bash
docker compose up --build
```

Reset Docker Environment
```bash
newgrp docker

sudo docker compose down -v

sudo docker compose up -d

sleep 5

java -jar target/districore-api-0.1.0.jar

Run with Test Profile

java -jar target/districore-api-0.1.0.jar --spring.profiles.active=test
```

Access Points

```bash
Application: http://localhost:8080
Swagger UI: http://localhost:8080/swagger-ui/index.html
API Base: /api/v1
Tenant Header: X-Tenant-Id: default
Default Credentials
Username: admin
Password: Admin123!
Tenant: default```
