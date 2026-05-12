package com.districore.platform.product;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HsnCodeRepository extends JpaRepository<HsnCode, UUID> {
    Optional<HsnCode> findByCode(String code);
    List<HsnCode> findByTenantId(String tenantId);
}
