package com.districore.platform.scheme;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SchemeRepository extends JpaRepository<Scheme, UUID> {
    List<Scheme> findByTenantId(String tenantId);
    Optional<Scheme> findByIdAndTenantId(UUID id, String tenantId);
}
