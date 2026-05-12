package com.districore.platform.distributor;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DistributorRepository extends JpaRepository<Distributor, UUID> {
    List<Distributor> findByTenantId(String tenantId);
    Optional<Distributor> findByIdAndTenantId(UUID id, String tenantId);
}
