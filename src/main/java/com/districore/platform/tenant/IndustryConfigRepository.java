package com.districore.platform.tenant;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface IndustryConfigRepository extends JpaRepository<IndustryConfig, UUID> {
    Optional<IndustryConfig> findByIndustryVerticalAndTenantId(IndustryVertical industryVertical, String tenantId);
}
