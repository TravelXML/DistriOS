package com.districore.platform.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WarehouseRepository extends JpaRepository<Warehouse, UUID> {
    List<Warehouse> findByTenantId(String tenantId);
    Optional<Warehouse> findByIdAndTenantId(UUID id, String tenantId);
}
