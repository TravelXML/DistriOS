# DistriCore API

**DistriCore : One platform for modern distribution.**

DistriCore is an API-first Distribution Management Platform designed for **FMCG, CPG, FMCD, OTC Pharma, and general distribution businesses**.

It connects companies, distributors, stockists, dealers, retailers, sales teams, warehouses, inventory, pricing, schemes, invoices, claims, loyalty, analytics, AI insights, and integrations into one modern distribution operating system.

---

## 1. Product Vision

DistriCore is built to become a configurable, multi-industry distribution backbone.

It supports:

- Distributor Management System, also known as DMS
- Sales Force Automation, also known as SFA
- Retailer self-ordering app
- Product catalogue and pricing engine
- Inventory and warehouse management
- Scheme and claim management
- GST/HSN-based invoice and tax handling
- Loyalty and retailer engagement
- AI-powered sales, route, outlet, and product insights
- ERP, payment, tax, SMS, WhatsApp, and notification integrations

---

## 2. Supported Industry Verticals

DistriCore is not limited to FMCG. Every tenant/company can be configured with an industry vertical.

Supported verticals:

```text
FMCG
CPG
FMCD
OTC_PHARMA
GENERAL_DISTRIBUTION
```

### FMCG / CPG

Typical capabilities:

- SKU-based product management
- UOM, pack size, case size
- MRP, margin, and price list
- Distributor-retailer mapping
- Primary and secondary sales
- Beat and route sales
- Schemes and claims
- Optional batch and expiry tracking

### FMCD

Typical capabilities:

- Serial number tracking
- Warranty tracking
- Dealer/channel partner model
- Installation status
- Demo unit tracking
- Replacement and warranty claims
- High-value inventory tracking

### OTC Pharma

Typical capabilities:

- Mandatory batch number
- Mandatory expiry date
- HSN and GST configuration
- Drug license support for distributor and retailer
- Stockist/chemist/channel mapping
- Near-expiry and expired stock handling
- Product recall support
- Composition and manufacturer fields
- Regulatory document attachment placeholders

---

## 3. Technology Stack

Recommended stack:

| Layer | Technology |
|---|---|
| Language | Java 21 or latest stable Java LTS |
| Backend Framework | Spring Boot |
| API | REST, OpenAPI, Swagger |
| Security | Spring Security, JWT, RBAC |
| Database | PostgreSQL |
| Cache | Redis |
| ORM | Spring Data JPA / Hibernate |
| DB Migration | Flyway |
| Build Tool | Maven or Gradle |
| Testing | JUnit 5, MockMvc, Testcontainers |
| Containerization | Docker, Docker Compose |
| Mapping | MapStruct or manual mappers |
| Validation | Jakarta Bean Validation |
| Future Events | Kafka / RabbitMQ |
| Observability | OpenTelemetry, Prometheus, Grafana |
| Search | Elasticsearch / OpenSearch |

---

## 4. Architecture

DistriCore starts as a **modular monolith** with clean domain boundaries.

This keeps development fast in the early stage while allowing future extraction into microservices.

```text
Frontend Apps
  ├── Admin Portal
  ├── Distributor Portal
  ├── Retailer App
  ├── SFA Mobile App
  └── Supervisor Dashboard

API Layer
  ├── REST APIs
  ├── Swagger / OpenAPI
  ├── JWT Security
  ├── RBAC
  ├── Tenant Filter
  └── Correlation ID Filter

DistriCore Backend
  ├── Common
  ├── Identity / Security
  ├── Tenant / Organization
  ├── Distributor
  ├── Retailer
  ├── Product
  ├── Pricing
  ├── Inventory
  ├── Order
  ├── Invoice
  ├── Scheme
  ├── Claim
  ├── Warranty
  ├── Product Recall
  ├── SFA
  ├── Retailer App
  ├── Loyalty
  ├── Complaint
  ├── Notification
  ├── Analytics
  ├── AI
  └── Integration

Data Layer
  ├── PostgreSQL
  ├── Redis
  ├── Object Storage
  ├── Event Bus
  └── Search Index
```

---

## 5. Project Structure

```text
districore-api/
  ├── src/
  │   ├── main/
  │   │   ├── java/com/districore/platform/
  │   │   │   ├── DistriCoreApplication.java
  │   │   │   ├── common/
  │   │   │   ├── security/
  │   │   │   ├── identity/
  │   │   │   ├── tenant/
  │   │   │   ├── organization/
  │   │   │   ├── distributor/
  │   │   │   ├── retailer/
  │   │   │   ├── product/
  │   │   │   ├── pricing/
  │   │   │   ├── inventory/
  │   │   │   ├── order/
  │   │   │   ├── invoice/
  │   │   │   ├── scheme/
  │   │   │   ├── claim/
  │   │   │   ├── warranty/
  │   │   │   ├── recall/
  │   │   │   ├── sfa/
  │   │   │   ├── retailerapp/
  │   │   │   ├── loyalty/
  │   │   │   ├── complaint/
  │   │   │   ├── notification/
  │   │   │   ├── analytics/
  │   │   │   ├── ai/
  │   │   │   └── integration/
  │   │   └── resources/
  │   │       ├── application.yml
  │   │       ├── application-local.yml
  │   │       └── db/migration/
  │   │           ├── V1__initial_schema.sql
  │   │           └── V2__seed_master_data.sql
  │   └── test/
  ├── Dockerfile
  ├── docker-compose.yml
  ├── pom.xml
  └── README.md
```

---

## 6. Core Modules

### Common

Includes shared infrastructure:

- API response wrapper
- Error response format
- Global exception handling
- Base entity
- Tenant-aware entity
- Audit fields
- Pagination helpers
- Correlation ID filter
- Idempotency support
- Common enums and constants

### Identity / Security

Includes:

- User management
- Role management
- Permission management
- JWT login
- Refresh token
- Logout
- Password hashing
- RBAC
- Method-level authorization

### Tenant / Organization

Includes:

- Company
- Business unit
- Branch
- Region
- Territory
- Route
- Beat
- Industry configuration

### Distributor

Includes:

- Distributor onboarding
- Branches
- Credit limits
- Territory mapping
- Sales team mapping
- Drug license support
- Distributor performance summary

### Retailer

Includes:

- Retailer onboarding
- KYC
- Category/channel
- Geo-location
- Credit limit
- Distributor mapping
- Beat mapping
- Drug license support
- Ledger summary

### Product

Includes:

- Product master
- SKU
- Brand
- Category
- Sub-category
- UOM
- HSN code
- GST configuration
- Pack size
- Case size
- Barcode
- Batch, expiry, serial number, and warranty configuration
- Composition and manufacturer fields

### Pricing

Includes:

- Price lists
- Retailer-specific pricing
- Distributor-specific pricing
- Region-specific pricing
- Effective date pricing
- Margin configuration
- Price calculation API

### Inventory

Includes:

- Warehouse/godown
- Stock in
- Stock out
- Stock adjustment
- Batch-wise stock
- Expiry-wise stock
- Serial-number-wise stock
- Damaged stock
- Expired stock
- Reserved stock
- Available stock
- Inventory transaction ledger
- Near-expiry alerts
- Low-stock alerts

### Order

Includes:

- Order creation
- Order confirmation
- Order cancellation
- Dispatch
- Delivery
- Order status history
- Order source support
- Idempotency key support

### Invoice

Includes:

- Invoice generation from order
- Invoice line items
- Tax calculation
- HSN-wise tax summary
- GST summary
- Invoice cancellation
- Credit note placeholder
- Debit note placeholder

### Scheme

Includes:

- Quantity discount
- Value discount
- Buy X Get Y
- Slab-based schemes
- Retailer/distributor/region/product-specific schemes
- Scheme simulation
- Explainable scheme calculation response

### Claim

Includes:

- Damage claim
- Expiry claim
- Scheme claim
- Rate difference claim
- Shortage claim
- Return claim
- Warranty claim
- Replacement claim
- Recall claim
- Approval workflow
- Attachment metadata

### SFA

Includes:

- Beat plan
- Today route
- Check-in
- Check-out
- Retailer visit
- Order capture
- Expense management
- Task management
- Supervisor dashboard
- Van sales placeholder

### Retailer App

Includes:

- Retailer home
- Product catalogue
- Recommended products
- Applicable schemes
- Cart
- Order placement
- Order tracking
- Complaints
- Loyalty summary

### AI

AI is kept as an adapter layer initially.

Includes mock/service interfaces for:

- Product recommendations
- Outlet pulse
- Route optimization
- Scheme simulation insights
- Sales analyzer
- Target disaggregation
- Smart nudges
- Supervisor insights

---

## 7. Key Domain Terms

### UOM

UOM means **Unit of Measure**.

Examples:

```text
PCS
KG
LTR
BOX
CASE
DOZEN
```

### HSN Code

HSN code means **Harmonized System of Nomenclature**.

It is used for tax classification and GST/invoicing.

### Pack Size

Pack size means the quantity inside one sellable unit.

Examples:

```text
500 ml bottle
1 kg packet
100 tablet strip
```

### Case Size

Case size means the number of sellable units inside one carton/case.

Examples:

```text
1 case = 24 bottles
1 carton = 12 packets
```

---

## 8. API Design Principles

DistriCore follows API-first design.

Rules:

- Every API starts with `/api/v1`
- Every feature has request and response DTOs
- JPA entities are never exposed directly
- Every API returns a consistent response format
- Every request has a correlation ID
- Every tenant-scoped request uses `X-Tenant-Id`
- Critical write APIs support `Idempotency-Key`
- List APIs support pagination
- Errors follow a standard error format
- Business logic lives in the service layer
- Repository access stays inside service layer
- Money values use `BigDecimal`
- Important changes are auditable

---

## 9. Standard API Response

### Success Response

```json
{
  "success": true,
  "message": "Operation completed successfully",
  "data": {},
  "correlationId": "2ff7a8c9-8a80-4712-b6e8-134ce8e99dd0"
}
```

### Error Response

```json
{
  "timestamp": "2026-05-12T10:30:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Invalid request payload",
  "path": "/api/v1/orders",
  "correlationId": "2ff7a8c9-8a80-4712-b6e8-134ce8e99dd0"
}
```

---

## 10. Multi-Tenancy

Every major business entity belongs to a tenant/company.

Tenant is passed using:

```http
X-Tenant-Id: <tenant-id>
```

Tenant isolation should prevent cross-company data access.

Core components:

- `TenantContext`
- `TenantFilter`
- `TenantAwareEntity`
- Tenant-aware repositories or service-level validations

---

## 11. Authentication and Authorization

DistriCore uses JWT-based authentication.

Default roles:

```text
SUPER_ADMIN
COMPANY_ADMIN
DISTRIBUTOR_ADMIN
SALES_MANAGER
SALES_REP
RETAILER
WAREHOUSE_USER
FINANCE_USER
SUPPORT_USER
```

---

## 12. Important Headers

| Header | Required | Purpose |
|---|---:|---|
| `Authorization` | Yes | Bearer JWT token |
| `X-Tenant-Id` | Yes for tenant APIs | Tenant/company isolation |
| `X-Correlation-Id` | Optional | Request tracing |
| `Idempotency-Key` | Required for selected write APIs | Prevent duplicate operations |

---

## 13. Main API Endpoints

### Auth

```http
POST /api/v1/auth/register
POST /api/v1/auth/login
POST /api/v1/auth/refresh-token
POST /api/v1/auth/logout
GET  /api/v1/auth/me
```

### Industry Config

```http
POST /api/v1/industry-config
GET  /api/v1/industry-config
GET  /api/v1/industry-config/{industryVertical}
PUT  /api/v1/industry-config/{industryVertical}
```

### Distributor

```http
POST   /api/v1/distributors
GET    /api/v1/distributors
GET    /api/v1/distributors/{id}
PUT    /api/v1/distributors/{id}
PATCH  /api/v1/distributors/{id}/status
POST   /api/v1/distributors/{id}/branches
GET    /api/v1/distributors/{id}/branches
POST   /api/v1/distributors/{id}/credit-limit
POST   /api/v1/distributors/{id}/drug-license
GET    /api/v1/distributors/{id}/performance-summary
```

### Retailer

```http
POST   /api/v1/retailers
GET    /api/v1/retailers
GET    /api/v1/retailers/{id}
PUT    /api/v1/retailers/{id}
PATCH  /api/v1/retailers/{id}/status
POST   /api/v1/retailers/{id}/kyc
POST   /api/v1/retailers/{id}/drug-license
GET    /api/v1/retailers/{id}/ledger
GET    /api/v1/retailers/{id}/orders
GET    /api/v1/retailers/{id}/schemes
GET    /api/v1/retailers/{id}/loyalty
```

### Product

```http
POST   /api/v1/products
GET    /api/v1/products
GET    /api/v1/products/{id}
PUT    /api/v1/products/{id}
PATCH  /api/v1/products/{id}/status
POST   /api/v1/products/{productId}/serial-numbers
GET    /api/v1/products/{productId}/serial-numbers
POST   /api/v1/product-categories
GET    /api/v1/product-categories
POST   /api/v1/brands
GET    /api/v1/brands
POST   /api/v1/uoms
GET    /api/v1/uoms
POST   /api/v1/hsn-codes
GET    /api/v1/hsn-codes
```

### Pricing

```http
POST   /api/v1/price-lists
GET    /api/v1/price-lists
GET    /api/v1/price-lists/{id}
POST   /api/v1/price-lists/{id}/items
GET    /api/v1/products/{productId}/prices
GET    /api/v1/retailers/{retailerId}/applicable-prices
POST   /api/v1/pricing/calculate
```

### Inventory

```http
GET    /api/v1/inventory
GET    /api/v1/inventory/warehouses/{warehouseId}
POST   /api/v1/inventory/stock-in
POST   /api/v1/inventory/stock-out
POST   /api/v1/inventory/adjustments
GET    /api/v1/inventory/transactions
GET    /api/v1/inventory/low-stock
GET    /api/v1/inventory/near-expiry
GET    /api/v1/inventory/expired-stock
GET    /api/v1/inventory/products/{productId}/availability
```

### Order

```http
POST   /api/v1/orders
GET    /api/v1/orders
GET    /api/v1/orders/{id}
PATCH  /api/v1/orders/{id}/confirm
PATCH  /api/v1/orders/{id}/cancel
PATCH  /api/v1/orders/{id}/dispatch
PATCH  /api/v1/orders/{id}/deliver
GET    /api/v1/orders/{id}/status-history
```

### Invoice

```http
POST   /api/v1/invoices/from-order/{orderId}
GET    /api/v1/invoices
GET    /api/v1/invoices/{id}
GET    /api/v1/invoices/{id}/tax-summary
PATCH  /api/v1/invoices/{id}/cancel
```

### Scheme

```http
POST   /api/v1/schemes
GET    /api/v1/schemes
GET    /api/v1/schemes/{id}
PUT    /api/v1/schemes/{id}
PATCH  /api/v1/schemes/{id}/status
POST   /api/v1/schemes/simulate
POST   /api/v1/orders/{orderId}/apply-schemes
GET    /api/v1/retailers/{retailerId}/eligible-schemes
```

### Claim

```http
POST   /api/v1/claims
GET    /api/v1/claims
GET    /api/v1/claims/{id}
PATCH  /api/v1/claims/{id}/approve
PATCH  /api/v1/claims/{id}/reject
POST   /api/v1/claims/{id}/attachments
GET    /api/v1/claims/reports/summary
```

### Warranty

```http
POST /api/v1/warranties
GET  /api/v1/warranties/{serialNumber}
POST /api/v1/warranty-claims
```

### Product Recall

```http
POST  /api/v1/product-recalls
GET   /api/v1/product-recalls
GET   /api/v1/product-recalls/{id}
PATCH /api/v1/product-recalls/{id}/close
```

### SFA

```http
GET    /api/v1/sfa/my-beats
GET    /api/v1/sfa/today-route
POST   /api/v1/sfa/check-in
POST   /api/v1/sfa/check-out
POST   /api/v1/sfa/retailer-visit
POST   /api/v1/sfa/orders
POST   /api/v1/sfa/expenses
GET    /api/v1/sfa/tasks
PATCH  /api/v1/sfa/tasks/{id}/complete
GET    /api/v1/sfa/supervisor/dashboard
```

### Retailer App

```http
GET    /api/v1/retailer-app/home
GET    /api/v1/retailer-app/products
GET    /api/v1/retailer-app/recommended-products
GET    /api/v1/retailer-app/schemes
POST   /api/v1/retailer-app/cart/items
GET    /api/v1/retailer-app/cart
POST   /api/v1/retailer-app/orders
GET    /api/v1/retailer-app/orders
GET    /api/v1/retailer-app/orders/{id}
POST   /api/v1/retailer-app/complaints
GET    /api/v1/retailer-app/loyalty
```

### AI

```http
GET    /api/v1/ai/product-recommendations?retailerId=
GET    /api/v1/ai/outlet-pulse/{retailerId}
POST   /api/v1/ai/route-optimization
POST   /api/v1/ai/scheme-simulation
GET    /api/v1/ai/sales-analyzer
POST   /api/v1/ai/target-disaggregation
GET    /api/v1/ai/smart-nudges
GET    /api/v1/ai/supervisor-insights
```

### Analytics

```http
GET /api/v1/analytics/sales-summary
GET /api/v1/analytics/distributor-performance
GET /api/v1/analytics/retailer-performance
GET /api/v1/analytics/product-performance
GET /api/v1/analytics/scheme-performance
GET /api/v1/analytics/stock-movement
GET /api/v1/analytics/sfa-productivity
GET /api/v1/analytics/claims-summary
```

### Integration

```http
POST /api/v1/webhooks
GET  /api/v1/webhooks
POST /api/v1/integrations/erp/push-order
POST /api/v1/integrations/erp/push-invoice
POST /api/v1/integrations/tax/validate-gstin
POST /api/v1/integrations/payment/callback
```

---

## 14. Order Flow

```text
1. Create order
2. Validate retailer
3. Validate distributor
4. Validate products
5. Calculate price
6. Apply eligible schemes
7. Reserve inventory
8. Confirm order
9. Generate invoice
10. Dispatch
11. Deliver
12. Maintain order status history
```

Order sources:

```text
ADMIN
DISTRIBUTOR
RETAILER_APP
SFA_APP
```

---

## 15. Inventory Rules

Every stock movement must create an `InventoryTransaction`.

Stock types:

```text
AVAILABLE
RESERVED
DAMAGED
EXPIRED
IN_TRANSIT
DEMO
RETURNED
```

Transaction types:

```text
STOCK_IN
STOCK_OUT
ADJUSTMENT
RESERVATION
RELEASE_RESERVATION
DISPATCH
RETURN
DAMAGE
EXPIRY
RECALL
WARRANTY_REPLACEMENT
```

---

## 16. Scheme Engine

Supported scheme types:

```text
PERCENTAGE_DISCOUNT
FLAT_DISCOUNT
BUY_X_GET_Y
SLAB_DISCOUNT
```

Scheme simulation response should include:

- Original order value
- Discount value
- Free items
- Final order value
- Applied schemes
- Explanation of why each scheme was applied

---

## 17. Pricing Engine

Pricing calculation should consider:

```text
Base price
Price list by distributor/region
Retailer-specific override
Distributor-specific override
Tax calculation
Final price
```

---

## 18. Tax Model

DistriCore supports GST-like tax configuration.

Tax components:

```text
CGST
SGST
IGST
```

Tax summary:

- Product-level tax
- HSN-wise tax summary
- Invoice-level GST summary

---

## 19. Events

DistriCore should publish or prepare internal events for:

```text
DistributorCreated
RetailerCreated
ProductCreated
PriceUpdated
InventoryAdjusted
OrderCreated
OrderConfirmed
OrderCancelled
InvoiceGenerated
ClaimSubmitted
ClaimApproved
SchemeApplied
SalesVisitCompleted
RetailerInactiveDetected
ProductRecallCreated
NearExpiryDetected
WarrantyClaimSubmitted
```

Initial version can keep events as internal application events or outbox records.

---

## 20. Local Setup

### Prerequisites

Install:

- Java 21+
- Maven or Gradle
- Docker
- Docker Compose
- PostgreSQL client, optional
- Redis client, optional

---

## 21. Run With Docker Compose

```bash
docker compose up -d
```

Application should start at:

```text
http://localhost:8080
```

Swagger should be available at:

```text
http://localhost:8080/swagger-ui/index.html
```

OpenAPI JSON:

```text
http://localhost:8080/v3/api-docs
```

---

## 22. Run Locally Without Docker

Start PostgreSQL and Redis first.

Then run:

```bash
./mvnw clean spring-boot:run
```

Or:

```bash
mvn clean spring-boot:run
```

---

## 23. Environment Configuration

Example `.env`:

```env
SPRING_PROFILES_ACTIVE=local

DB_HOST=localhost
DB_PORT=5432
DB_NAME=districore
DB_USERNAME=districore
DB_PASSWORD=districore

REDIS_HOST=localhost
REDIS_PORT=6379

JWT_SECRET=change-this-secret
JWT_EXPIRATION_MINUTES=60
JWT_REFRESH_EXPIRATION_DAYS=7
```

---

## 24. Default Seed Data

Seed data should include:

- One company
- Default roles
- Default permissions
- Admin user
- Sample distributor
- Sample retailer
- Product categories
- Brands
- UOMs
- HSN codes
- Sample FMCG product
- Sample FMCD product
- Sample OTC pharma product
- Sample warehouse
- Sample inventory
- Sample price list
- Sample scheme

### Default Admin

```text
Email: admin@districore.com
Password: Admin@123
```

Change this password immediately in production.

---

## 25. Sample cURL Commands

### Login

```bash
curl -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "admin@districore.com",
    "password": "Admin@123"
  }'
```

### Create Distributor

```bash
curl -X POST http://localhost:8080/api/v1/distributors \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <token>" \
  -H "X-Tenant-Id: <tenant-id>" \
  -d '{
    "code": "DIST-BLR-001",
    "name": "Bangalore Central Distributor",
    "gstin": "29ABCDE1234F1Z5",
    "pan": "ABCDE1234F",
    "contactPerson": "Ramesh Kumar",
    "mobile": "9876543210",
    "email": "ramesh@example.com",
    "city": "Bengaluru",
    "state": "Karnataka",
    "pincode": "560066",
    "country": "India",
    "creditLimit": 5000000
  }'
```

### Create Product

```bash
curl -X POST http://localhost:8080/api/v1/products \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <token>" \
  -H "X-Tenant-Id: <tenant-id>" \
  -d '{
    "skuCode": "COKE-750ML",
    "productName": "Coke 750 ML Bottle",
    "industryVertical": "FMCG",
    "brandId": "<brand-id>",
    "categoryId": "<category-id>",
    "uom": "PCS",
    "packSize": "750",
    "packSizeUom": "ML",
    "caseSize": 24,
    "hsnCode": "22021010",
    "gstRate": 18,
    "mrp": 45.00,
    "basePrice": 36.00,
    "barcode": "890000000001",
    "batchRequired": false,
    "expiryRequired": false,
    "serialNumberRequired": false,
    "warrantyRequired": false
  }'
```

### Stock In

```bash
curl -X POST http://localhost:8080/api/v1/inventory/stock-in \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <token>" \
  -H "X-Tenant-Id: <tenant-id>" \
  -H "Idempotency-Key: stock-in-001" \
  -d '{
    "warehouseId": "<warehouse-id>",
    "productId": "<product-id>",
    "quantity": 1000,
    "stockType": "AVAILABLE",
    "batchNumber": "BATCH-001",
    "expiryDate": "2027-12-31",
    "remarks": "Initial stock"
  }'
```

### Create Order

```bash
curl -X POST http://localhost:8080/api/v1/orders \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <token>" \
  -H "X-Tenant-Id: <tenant-id>" \
  -H "Idempotency-Key: order-001" \
  -d '{
    "retailerId": "<retailer-id>",
    "distributorId": "<distributor-id>",
    "orderSource": "RETAILER_APP",
    "items": [
      {
        "productId": "<product-id>",
        "quantity": 10,
        "uom": "CASE"
      }
    ],
    "remarks": "Urgent delivery required"
  }'
```

### Scheme Simulation

```bash
curl -X POST http://localhost:8080/api/v1/schemes/simulate \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <token>" \
  -H "X-Tenant-Id: <tenant-id>" \
  -d '{
    "retailerId": "<retailer-id>",
    "distributorId": "<distributor-id>",
    "items": [
      {
        "productId": "<product-id>",
        "quantity": 20
      }
    ]
  }'
```

---

## 26. Testing

Run tests:

```bash
./mvnw test
```

Or:

```bash
mvn test
```

Recommended test coverage:

- Auth login
- Distributor creation
- Retailer creation
- Product creation
- Stock-in
- Order creation
- Scheme simulation
- Invoice generation
- Claim submission
- Warranty creation
- Near-expiry inventory query
- Product recall creation

---

## 27. Build

```bash
./mvnw clean package
```

Run packaged jar:

```bash
java -jar target/districore-api.jar
```

---

## 28. Docker Build

```bash
docker build -t districore-api .
```

Run:

```bash
docker run -p 8080:8080 districore-api
```

---

## 29. Future Microservice Split Strategy

The modular monolith can later be split into microservices.

Suggested split:

```text
identity-service
tenant-service
product-service
pricing-service
inventory-service
order-service
invoice-service
scheme-service
claim-service
sfa-service
retailer-app-service
loyalty-service
notification-service
analytics-service
ai-service
integration-service
```

Recommended extraction order:

1. Identity and tenant
2. Product and pricing
3. Inventory
4. Order and invoice
5. Scheme and claim
6. SFA and retailer app
7. Analytics and AI
8. Integration and notification

---

## 30. Production Readiness Checklist

Before production, ensure:

- Strong JWT secret
- Password policy
- HTTPS enforced
- CORS restricted
- Tenant isolation tested
- RBAC tested
- Database indexes added
- Slow query monitoring enabled
- Audit logs enabled
- Backup and restore tested
- Redis memory limits configured
- Rate limiting enabled
- Logs include correlation ID
- API gateway configured
- Observability enabled
- Docker image scanning enabled
- Secrets moved to secret manager
- Default admin password changed
- Flyway migration reviewed
- Sensitive data encrypted where needed

---

## 31. Implementation Status Template

Use this section in project updates.

| Module | Status | Notes |
|---|---|---|
| Common | Planned | API response, exceptions, audit, tenant |
| Security | Planned | JWT, RBAC, refresh token |
| Distributor | Planned | Core DMS module |
| Retailer | Planned | KYC, ledger, mapping |
| Product | Planned | Multi-industry product master |
| Pricing | Planned | Price list and calculation |
| Inventory | Planned | Stock ledger, batch, expiry, serial |
| Order | Planned | Order lifecycle |
| Invoice | Planned | GST and HSN summary |
| Scheme | Planned | Simulation and calculation |
| Claim | Planned | Approval flow |
| SFA | Planned | Beat and visit management |
| Retailer App | Planned | Self-ordering APIs |
| Loyalty | Planned | Points and tiers |
| AI | Planned | Adapter/mock first |
| Analytics | Planned | Summary APIs |
| Integration | Planned | ERP/payment/webhook placeholders |

---

## 32. License

This project is proprietary unless a separate license is provided.

---

## 33. Maintainer

**DistriCore Platform Team**

For questions, contact us: astartupcto@gmail.com
