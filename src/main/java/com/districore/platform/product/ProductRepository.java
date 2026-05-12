package com.districore.platform.product;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findBySkuCodeAndTenantId(String skuCode, String tenantId);
    List<Product> findByTenantId(String tenantId);
    Optional<Product> findByIdAndTenantId(UUID id, String tenantId);
}
