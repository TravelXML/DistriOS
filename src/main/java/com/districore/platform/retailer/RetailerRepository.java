package com.districore.platform.retailer;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RetailerRepository extends JpaRepository<Retailer, UUID> {
    List<Retailer> findByTenantId(String tenantId);
    Optional<Retailer> findByIdAndTenantId(UUID id, String tenantId);
}
