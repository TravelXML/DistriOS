package com.districore.platform.warranty;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface WarrantyRepository extends JpaRepository<Warranty, UUID> {
    Optional<Warranty> findBySerialNumberAndTenantId(String serialNumber, String tenantId);
}
