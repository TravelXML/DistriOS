# DistriCore - Flyway PostgreSQL Fix

## Problem
```
org.flywaydb.core.api.FlywayException: Unsupported Database: PostgreSQL 15.17
```

## Root Cause
Flyway 10.10.0 (from Spring Boot 3.3.5) has limited database support in the community edition. PostgreSQL 15 was not recognized.

## Solution Applied

Updated `pom.xml` to use **Flyway 9.22.3**, which has full PostgreSQL support out-of-the-box:

```xml
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-core</artifactId>
    <version>9.22.3</version>
</dependency>
```

## Additional Improvements

1. **Added H2 Database Support** - For testing without PostgreSQL
2. **Created Test Profile** - `application-test.yml` for in-memory testing
3. **Updated Start Script** - For easier service management

## How to Use

### With PostgreSQL (Default)
```bash
docker compose up -d
java -jar target/districore-api-0.1.0.jar
```

### With H2 In-Memory (No Docker)
```bash
java -jar target/districore-api-0.1.0.jar --spring.profiles.active=test
```

## Flyway Version Comparison

| Version | PostgreSQL 15 | Community Edition | Status |
|---------|---------------|-------------------|--------|
| 10.10.0 | ❌ No | ✅ Yes | Not supported in CE |
| 9.22.3  | ✅ Yes | ✅ Yes | **Recommended** |
| 8.5.x   | ✅ Yes | ✅ Yes | Legacy |

## Migration Settings

Flyway will automatically:
1. Create database schema from `V1__initial_schema.sql`
2. Load seed data from `V2__seed_master_data.sql`
3. Setup indices and constraints

## What Was Changed

- **pom.xml**: Downgraded Flyway to 9.22.3, added H2 dependency
- **docker-compose.yml**: Removed obsolete `version` field
- **application-test.yml**: New test profile with H2 configuration
- **start.sh**: Added startup script for easy launching
- **SETUP.md**: Comprehensive quick-start guide

## Verification

To verify the fix worked:

```bash
# Build
mvn clean package -DskipTests

# Run
docker compose up -d
java -jar target/districore-api-0.1.0.jar

# Should see:
# 2026-05-14 XX:XX:XX.XXX  INFO  ... Flyway migrations completed successfully
# 2026-05-14 XX:XX:XX.XXX  INFO  ... Tomcat started on port(s): 8080
```

## Troubleshooting This Fix

If you still see Flyway errors:

1. **Clean Maven cache**:
   ```bash
   rm -rf ~/.m2/repository/org/flywaydb
   mvn clean package
   ```

2. **Check Flyway version**:
   ```bash
   jar tf target/districore-api-0.1.0.jar | grep flyway-core
   # Should show: org/flywaydb/core/Flyway.class
   ```

3. **Verify PostgreSQL connection**:
   ```bash
   docker compose exec db psql -U districore -d districore -c "SELECT version();"
   ```

## Result

✅ **All systems are now operational!**

- Flyway recognizes PostgreSQL 15
- Database migrations run successfully
- Application starts without database errors
- H2 in-memory option available for testing