package com.districore.platform.recall;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRecallRepository extends JpaRepository<ProductRecall, UUID> {
    List<ProductRecall> findByTenantId(String tenantId);
    Optional<ProductRecall> findByIdAndTenantId(UUID id, String tenantId);
}
