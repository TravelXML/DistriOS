package com.districore.platform.product;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UomRepository extends JpaRepository<Uom, UUID> {
    Optional<Uom> findByCode(String code);
    List<Uom> findByTenantId(String tenantId);
}
