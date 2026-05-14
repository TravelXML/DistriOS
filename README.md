# DistriOS - DistriCore a DMS API

<p align="center">
  <strong>API-first Distribution Management System for FMCG, CPG, FMCD, OTC Pharma, rurban markets, and general distribution businesses.</strong>
</p>

<p align="center">
  <em>DistriCore - One platform for modern retail distribution.</em>
</p>

<p align="center">
  <img alt="Java" src="https://img.shields.io/badge/Java-21%2B-blue">
  <img alt="Spring Boot" src="https://img.shields.io/badge/Spring%20Boot-API%20Backend-brightgreen">
  <img alt="PostgreSQL" src="https://img.shields.io/badge/PostgreSQL-Database-blue">
  <img alt="Redis" src="https://img.shields.io/badge/Redis-Cache-red">
  <img alt="Docker" src="https://img.shields.io/badge/Docker-Ready-blue">
  <img alt="OpenAPI" src="https://img.shields.io/badge/OpenAPI-Swagger-orange">
  <img alt="Architecture" src="https://img.shields.io/badge/Architecture-Modular%20Monolith-purple">
</p>

---

## Overview

**DistriOS** is the repository for **DistriCore API**, a modern, API-first **Distribution Management Platform** built for companies, distributors, stockists, dealers, retailers, field sales teams, warehouses, and multi-company distribution networks.

It is designed to support the complete distribution lifecycle:

- Distributor Management System, also known as **DMS**
- Sales Force Automation, also known as **SFA**
- Retailer ordering and engagement APIs
- Inventory and warehouse management
- Product catalogue, UOM, HSN, pricing, tax, and schemes
- Orders, invoices, claims, warranty, product recall, and loyalty
- AI-powered recommendations, outlet pulse, route optimization, smart nudges, and analytics
- ERP, tax, payment, webhook, SMS, WhatsApp, and notification integrations

DistriCore is not designed only for FMCG. It is configurable for multiple industry verticals including **FMCG, CPG, FMCD, OTC Pharma, and general distribution**.

---

## SEO Keywords

`Distribution Management System`, `DMS Software`, `Distributor Management System`, `FMCG DMS`, `CPG Distribution Software`, `FMCD Distribution Platform`, `OTC Pharma Distribution`, `Rurban Distribution`, `Sales Force Automation`, `Retailer App APIs`, `Inventory Management`, `Order Management`, `Scheme Management`, `Claims Management`, `Java Spring Boot REST API`, `API-first Platform`, `Multi-Tenant SaaS`, `Modular Monolith`.

---

## Why DistriCore?

Traditional distribution systems are usually fragmented. Distributors manage orders in one place, inventory somewhere else, claims manually, schemes through spreadsheets, and sales teams through disconnected tools.

DistriCore solves this by creating a unified API-first backend that can power:

- Admin portals
- Distributor portals
- Retailer apps
- SFA mobile apps
- Supervisor dashboards
- AI and analytics layers
- ERP and external integrations

---

## What Is Rurban?

**Rurban** means **Rural + Urban**.

In distribution and retail, rurban markets are semi-urban, small-town, and fast-growing rural markets that show urban-like demand but still have rural-style distribution challenges.

Examples:

- Tier 3 and Tier 4 cities
- Large villages
- Semi-urban belts
- Rural markets near cities
- Emerging retail clusters
- Small-town distributor networks

In DMS context, a **rurban distribution platform** helps distributors manage sales, stock, orders, schemes, claims, and field operations across spread-out retail markets where internet connectivity, route discipline, stock visibility, and manual ordering are common challenges.

---

## Supported Industry Verticals

DistriCore uses an `IndustryVertical` model so every company or tenant can configure its own business behavior.

```java
public enum IndustryVertical {
    FMCG,
    CPG,
    FMCD,
    OTC_PHARMA,
    GENERAL_DISTRIBUTION
}
```

### FMCG / CPG

For fast-moving consumer goods and consumer packaged goods.

Key capabilities:

- SKU-based product management
- UOM, pack size, case size
- MRP, base price, price list, and margins
- Primary and secondary sales
- Distributor-retailer mapping
- Beat planning and route sales
- Schemes, claims, promotions, and loyalty
- Optional batch and expiry tracking

### FMCD

For fast-moving consumer durables and high-value channel sales.

Key capabilities:

- Serial number tracking
- Warranty tracking
- Dealer and channel partner management
- Product installation status
- Demo unit tracking
- Replacement and warranty claims
- High-value inventory visibility

### OTC Pharma

For over-the-counter medicine and pharma distribution.

Key capabilities:

- Mandatory batch tracking
- Mandatory expiry tracking
- HSN and GST compliance
- Distributor and retailer drug license support
- Stockist, chemist, and channel mapping
- Near-expiry and expired stock alerts
- Product recall handling
- Composition, manufacturer, and regulatory fields

### General Distribution

For configurable B2B distribution models.

Key capabilities:

- Flexible product attributes
- Custom inventory rules
- Multi-company distributor operations
- Generic pricing, order, claim, and reporting workflows

---

## Core Capabilities

| Capability | Description |
|---|---|
| Multi-Tenant SaaS | Tenant isolation using `X-Tenant-Id` |
| API-First Design | REST APIs under `/api/v1` with Swagger/OpenAPI |
| DMS | Distributor, retailer, warehouse, order, invoice, claims |
| SFA | Beat planning, visits, check-in/out, expenses, tasks |
| Retailer App APIs | Product catalogue, cart, order, loyalty, complaints |
| Inventory | Stock ledger, batch, expiry, serial number, damaged and reserved stock |
| Pricing Engine | Price list, distributor pricing, retailer pricing, tax calculation |
| Scheme Engine | Discounts, slabs, Buy X Get Y, simulations, explainability |
| Claims | Damage, expiry, shortage, rate difference, warranty, replacement, recall |
| OTC Pharma | Drug license, expiry alerts, recalls, composition and manufacturer data |
| FMCD | Serial numbers, warranty, demo units, replacement flow |
| AI Layer | Recommendations, outlet pulse, route optimization, smart nudges |
| Analytics | Sales, distributor, retailer, product, scheme, claim and SFA reports |
| Integrations | ERP, tax validation, payment callbacks, webhooks, notifications |

---

## Technology Stack

| Layer | Technology |
|---|---|
| Language | Java 21+ |
| Framework | Spring Boot |
| API | REST, OpenAPI, Swagger UI |
| Security | Spring Security, JWT, RBAC |
| Database | PostgreSQL |
| Cache | Redis |
| ORM | Spring Data JPA / Hibernate |
| Migration | Flyway |
| Validation | Jakarta Bean Validation |
| Testing | JUnit 5, MockMvc, Testcontainers |
| Build | Maven |
| Container | Docker, Docker Compose |
| Future Events | Kafka / RabbitMQ |
| Observability | OpenTelemetry, Prometheus, Grafana |
| Search | Elasticsearch / OpenSearch |
| Storage | Object storage for attachments |

---

## Architecture

DistriCore starts as a **modular monolith** with strong domain boundaries.

This keeps development simple and fast in the early stage while keeping the codebase ready for future microservice extraction.

```text
Frontend / Consumers
  ├── Admin Portal
  ├── Distributor Portal
  ├── Retailer Mobile App
  ├── SFA Mobile App
  ├── Supervisor Dashboard
  ├── ERP Systems
  └── AI / Analytics Services

API Layer
  ├── REST APIs
  ├── Swagger / OpenAPI
  ├── JWT Security
  ├── RBAC
  ├── Tenant Filter
  ├── Correlation ID Filter
  └── Idempotency Support

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

Data / Infra
  ├── PostgreSQL
  ├── Redis
  ├── Flyway
  ├── Docker
  ├── Optional Kafka / RabbitMQ
  ├── Optional Object Storage
  └── Optional Search Index
```

---

## Project Structure

```text
DistriOS/
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

## Domain Modules

### Common

Shared platform infrastructure:

- API response wrapper
- Global exception handling
- Error response structure
- Base entity and tenant-aware entity
- Audit fields
- Pagination helpers
- Correlation ID filter
- Idempotency support
- Common enums and constants

### Identity and Security

Authentication and authorization:

- Users
- Roles
- Permissions
- JWT login
- Refresh token
- Logout
- Password hashing
- Role-based access control
- Method-level authorization

### Tenant and Organization

Multi-company and business hierarchy:

- Company
- Business unit
- Branch
- Region
- Territory
- Route
- Beat
- Industry configuration

### Distributor

Distributor lifecycle management:

- Distributor onboarding
- Distributor branches
- Credit limit
- Territory mapping
- Sales team mapping
- OTC pharma drug license support
- Performance summary

### Retailer

Retailer lifecycle management:

- Retailer onboarding
- Retailer KYC
- Category and channel
- Geo-location
- Credit limit
- Distributor mapping
- Beat mapping
- Ledger summary
- OTC pharma drug license support

### Product

Multi-industry product master:

- SKU
- Brand
- Category
- Sub-category
- UOM
- HSN code
- GST classification
- Pack size
- Case size
- Barcode
- Batch, expiry, serial number, warranty flags
- Composition
- Manufacturer
- Regulatory category

### Pricing

Pricing and margin logic:

- Price lists
- Price list items
- Distributor-specific pricing
- Retailer-specific pricing
- Region-specific pricing
- Effective date pricing
- Price calculation API

### Inventory

Stock and warehouse control:

- Warehouse / godown
- Stock in and stock out
- Stock adjustment
- Batch-wise stock
- Expiry-wise stock
- Serial-number-wise stock
- Damaged stock
- Expired stock
- Reserved stock
- Available stock
- Low-stock alerts
- Near-expiry alerts
- Product recall stock impact

### Order

Order lifecycle:

- Order creation
- Retailer and distributor validation
- Product validation
- Price calculation
- Scheme application
- Inventory reservation
- Confirmation
- Dispatch
- Delivery
- Status history

### Invoice

Invoice and tax:

- Invoice generation from order
- Invoice line items
- GST calculation
- CGST / SGST / IGST
- HSN-wise tax summary
- Invoice cancellation
- Credit note and debit note placeholders

### Scheme

Promotion and scheme engine:

- Percentage discount
- Flat discount
- Buy X Get Y
- Slab discounts
- Product, brand, category, distributor, region, and retailer-level schemes
- Scheme simulation
- Explainable scheme calculation

### Claim

Claims and approval workflow:

- Damage claim
- Expiry claim
- Scheme claim
- Rate difference claim
- Shortage claim
- Return claim
- Warranty claim
- Replacement claim
- Recall claim
- Attachments metadata
- Approval history

### SFA

Sales Force Automation:

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

Retailer self-service:

- Home
- Product catalogue
- Recommended products
- Applicable schemes
- Cart
- Order placement
- Order tracking
- Complaints
- Loyalty summary

### AI

AI adapter layer:

- Product recommendations
- Outlet pulse
- Route optimization
- Scheme simulation insights
- Sales analyzer
- Target disaggregation
- Smart nudges
- Supervisor insights

### Integration

External system connectivity:

- ERP order push
- ERP invoice push
- Tax validation
- Payment callback
- Webhook management
- Event publishing placeholder

---

## Key Domain Terms

### UOM

**Unit of Measure** — how a product is measured, stocked, or sold.

Examples:

```text
PCS, KG, LTR, BOX, CASE, DOZEN
```

### HSN Code

**Harmonized System of Nomenclature** — tax classification code used for GST and invoicing.

### Pack Size

Quantity inside one sellable unit.

Examples:

```text
500 ml bottle
1 kg packet
100 tablet strip
```

### Case Size

Number of sellable units inside one carton/case.

Examples:

```text
1 case = 24 bottles
1 carton = 12 packets
```

---

## API Design Principles

- All APIs are versioned under `/api/v1`
- REST-first design
- OpenAPI / Swagger documentation
- DTO-based request and response models
- No direct JPA entity exposure
- Consistent success and error responses
- Multi-tenant isolation using `X-Tenant-Id`
- Correlation ID for traceability
- Idempotency key support for critical writes
- Pagination for list APIs
- RBAC and JWT security
- Service-layer business logic
- Repository access only inside service layer
- `BigDecimal` for money
- Flyway for schema migrations

---

## Standard API Response

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

## Important Headers

| Header | Required | Purpose |
|---|---:|---|
| `Authorization` | Yes | Bearer JWT token |
| `X-Tenant-Id` | Yes for tenant APIs | Tenant/company isolation |
| `X-Correlation-Id` | Optional | Request tracing |
| `Idempotency-Key` | Required for selected write APIs | Prevent duplicate writes |

---

## API Endpoint Summary

### Auth

```http
POST /api/v1/auth/register
POST /api/v1/auth/login
POST /api/v1/auth/refresh-token
POST /api/v1/auth/logout
GET  /api/v1/auth/me
```

### Users

```http
POST   /api/v1/users
GET    /api/v1/users
GET    /api/v1/users/{id}
PUT    /api/v1/users/{id}
PATCH  /api/v1/users/{id}/status
POST   /api/v1/users/{id}/roles
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

### Loyalty

```http
POST   /api/v1/loyalty/accounts
GET    /api/v1/loyalty/accounts/{retailerId}
POST   /api/v1/loyalty/earn
POST   /api/v1/loyalty/redeem
GET    /api/v1/loyalty/transactions
```

### Complaint

```http
POST   /api/v1/complaints
GET    /api/v1/complaints
GET    /api/v1/complaints/{id}
PATCH  /api/v1/complaints/{id}/assign
PATCH  /api/v1/complaints/{id}/resolve
PATCH  /api/v1/complaints/{id}/close
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

## Order Flow

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

## Inventory Rules

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

## Scheme Engine

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

## Pricing Engine

Pricing calculation should consider:

- Base price
- Price list by distributor or region
- Retailer-specific override
- Distributor-specific override
- GST or tax calculation
- Final price

---

## GST / Tax Model

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

## Events

DistriCore can publish or prepare internal events for:

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

Initial versions can keep events as internal application events or outbox records.

---

## Getting Started

### Prerequisites

Install:

- Java 21+
- Maven 3.6+
- Docker
- Docker Compose
- PostgreSQL 15+
- Redis 7+

---

## Clone and Build

```bash
git clone https://github.com/TravelXML/DistriOS.git
cd DistriOS
mvn clean package
```

---

## Run With Docker Compose

```bash
docker compose up --build
```

Application:

```text
http://localhost:8080
```

Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

OpenAPI JSON:

```text
http://localhost:8080/v3/api-docs
```

---

## Run Locally

Start PostgreSQL and Redis first.

Then run:

```bash
mvn clean spring-boot:run
```

Or run the packaged JAR:

```bash
java -jar target/districore-api-0.1.0.jar
```

Run with test profile:

```bash
java -jar target/districore-api-0.1.0.jar --spring.profiles.active=test
```

---

## Docker Commands

```bash
docker compose up -d
docker compose ps
docker compose logs -f app
docker compose down
```

Reset local environment:

```bash
docker compose down -v
docker compose up -d
```

---

## Environment Variables

Example local configuration:

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

## Default Credentials

```text
Username: admin
Password: Admin123!
Tenant ID: default
```

Change the default password before production use.

---

## Sample API Calls

### Login

```bash
curl -X POST http://localhost:8080/api/v1/auth/login   -H "Content-Type: application/json"   -H "X-Tenant-Id: default"   -d '{
    "username": "admin",
    "password": "Admin123!"
  }'
```

### Create Distributor

```bash
curl -X POST http://localhost:8080/api/v1/distributors   -H "Content-Type: application/json"   -H "Authorization: Bearer <token>"   -H "X-Tenant-Id: default"   -d '{
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
curl -X POST http://localhost:8080/api/v1/products   -H "Content-Type: application/json"   -H "Authorization: Bearer <token>"   -H "X-Tenant-Id: default"   -d '{
    "skuCode": "COKE-750ML",
    "productName": "Coke 750 ML Bottle",
    "industryVertical": "FMCG",
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
curl -X POST http://localhost:8080/api/v1/inventory/stock-in   -H "Content-Type: application/json"   -H "Authorization: Bearer <token>"   -H "X-Tenant-Id: default"   -H "Idempotency-Key: stock-in-001"   -d '{
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
curl -X POST http://localhost:8080/api/v1/orders   -H "Content-Type: application/json"   -H "Authorization: Bearer <token>"   -H "X-Tenant-Id: default"   -H "Idempotency-Key: order-001"   -d '{
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
curl -X POST http://localhost:8080/api/v1/schemes/simulate   -H "Content-Type: application/json"   -H "Authorization: Bearer <token>"   -H "X-Tenant-Id: default"   -d '{
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

## Database Migrations

DistriCore uses Flyway.

Core migration files:

```text
V1__initial_schema.sql
V2__seed_master_data.sql
```

Seed data should include:

- Company
- Roles
- Permissions
- Admin user
- Distributor
- Retailer
- Product categories
- Brands
- UOMs
- HSN codes
- Sample FMCG product
- Sample FMCD product
- Sample OTC pharma product
- Warehouse
- Inventory
- Price list
- Scheme

---

## Testing

Run tests:

```bash
mvn test
```

Recommended coverage:

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

## Build

```bash
mvn clean package
```

Run packaged JAR:

```bash
java -jar target/districore-api-0.1.0.jar
```

---

## What Is Fully Implemented

- Core multi-tenant domain design
- API-first modular monolith structure
- Auth and RBAC foundations
- Industry configuration model
- Distributor and retailer lifecycle APIs
- Product master, category, brand, UOM, HSN support
- Pricing and invoice foundation
- Inventory transaction capture
- Order lifecycle and status history
- Claims, warranty, product recall, and complaint structure
- Loyalty account management
- Flyway migration and seed data structure
- Docker Compose support
- Swagger UI support

---

## Structurally Ready as Placeholder

- AI adapter APIs
- ERP integration hooks
- Payment callback integration
- SMS, WhatsApp, and email notification adapters
- Advanced analytics
- Advanced pricing overrides
- Advanced scheme engine
- Attachment and object storage
- Outbox pattern and event streaming
- Advanced audit and user activity trails

---

## Roadmap

### Phase 1 — Core DMS

- Auth and RBAC
- Company and tenant setup
- Distributor onboarding
- Retailer onboarding
- Product master
- UOM, HSN, brand, category
- Inventory stock-in
- Order creation
- Basic pricing
- Basic invoice

### Phase 2 — Commercial Engine

- Price list
- Tax engine
- Scheme engine
- Claim management
- Ledger summaries
- Credit limit
- Order confirmation and dispatch

### Phase 3 — SFA and Retailer App

- Beat planning
- Route planning
- Field check-in/out
- Retailer visit
- Salesman order capture
- Retailer catalogue
- Cart and self-ordering
- Loyalty and complaint management

### Phase 4 — Industry-Specific Depth

- FMCD serial number and warranty
- OTC pharma batch, expiry, and recall
- Rurban distributor workflows
- Multi-company distributor operations
- Advanced warehouse and return flows

### Phase 5 — AI and Analytics

- Product recommender
- Outlet pulse
- Route optimization
- Sales analyzer
- Target disaggregation
- Scheme simulation insights
- Smart nudges
- Supervisor insights

### Phase 6 — Enterprise Scale

- Kafka / RabbitMQ
- Outbox pattern
- OpenTelemetry
- Prometheus and Grafana
- Elasticsearch / OpenSearch
- Object storage
- API gateway
- Multi-region deployment

---

## Future Microservice Split Strategy

The modular monolith can later be split into independent services.

Suggested service split:

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

## Production Readiness Checklist

Before production:

- Replace default admin password
- Use a strong JWT secret
- Enforce HTTPS
- Restrict CORS
- Validate tenant isolation
- Enforce RBAC at API and method level
- Add database indexes
- Enable slow query monitoring
- Enable audit logs
- Configure backup and restore
- Configure Redis memory limits
- Add rate limiting
- Add API gateway
- Add structured logging with correlation ID
- Enable OpenTelemetry
- Scan Docker images
- Move secrets to secret manager
- Review Flyway migrations
- Encrypt sensitive data where required

---

## GitHub Topics

Recommended repository topics:

```text
java
spring-boot
dms
distribution-management
distributor-management-system
fmcg
cpg
fmcd
otc-pharma
rurban
retail-tech
sfa
inventory-management
order-management
pricing-engine
scheme-management
claims-management
multi-tenant
api-first
openapi
swagger
postgresql
redis
docker
modular-monolith
```

---

## Suggested GitHub Description

```text
API-first Distribution Management System for FMCG, CPG, FMCD, OTC Pharma, rurban markets, and multi-company distributors, built with Java Spring Boot.
```

---

## Contributing

1. Follow the modular architecture.
2. Keep APIs versioned under `/api/v1`.
3. Use DTOs for all request and response models.
4. Write tests for new features.
5. Update Swagger/OpenAPI documentation.
6. Maintain backward compatibility.
7. Use consistent error handling.
8. Keep tenant isolation in mind for every business query.

---

## License

This project is proprietary unless a separate open-source license is added.

---

## Maintainer

**DistriCore Platform Team**
astarupcto@gmail.com

Repository: `TravelXML/DistriOS`
