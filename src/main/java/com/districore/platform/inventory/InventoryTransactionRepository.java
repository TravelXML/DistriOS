package com.districore.platform.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface InventoryTransactionRepository extends JpaRepository<InventoryTransaction, UUID> {
    List<InventoryTransaction> findByTenantId(String tenantId);
    List<InventoryTransaction> findByProductIdAndTenantId(UUID productId, String tenantId);
    java.util.Optional<InventoryTransaction> findByIdempotencyKeyAndTenantId(String idempotencyKey, String tenantId);
}
