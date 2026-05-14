package com.districore.platform.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductSerialNumberRepository extends JpaRepository<ProductSerialNumber, UUID> {
    List<ProductSerialNumber> findByProductIdAndTenantId(UUID productId, String tenantId);
}
