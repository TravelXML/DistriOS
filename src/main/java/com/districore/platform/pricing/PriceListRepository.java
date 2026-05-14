package com.districore.platform.pricing;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PriceListRepository extends JpaRepository<PriceList, UUID> {
    List<PriceList> findByTenantId(String tenantId);
    Optional<PriceList> findByIdAndTenantId(UUID id, String tenantId);
    List<PriceList> findByRetailerIdAndTenantId(String retailerId, String tenantId);
    List<PriceList> findByDistributorIdAndTenantId(String distributorId, String tenantId);
}
