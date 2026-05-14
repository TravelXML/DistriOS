package com.districore.platform.claim;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClaimRepository extends JpaRepository<Claim, UUID> {
    List<Claim> findByTenantId(String tenantId);
    Optional<Claim> findByIdAndTenantId(UUID id, String tenantId);
    Optional<Claim> findByIdempotencyKeyAndTenantId(String idempotencyKey, String tenantId);
}
