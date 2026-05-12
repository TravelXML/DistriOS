package com.districore.platform.order;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByTenantId(String tenantId);
    Optional<Order> findByIdAndTenantId(UUID id, String tenantId);
    Optional<Order> findByIdempotencyKeyAndTenantId(String key, String tenantId);
}
