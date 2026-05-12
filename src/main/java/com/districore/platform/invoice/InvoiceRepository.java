package com.districore.platform.invoice;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {
    List<Invoice> findByTenantId(String tenantId);
    Optional<Invoice> findByIdAndTenantId(UUID id, String tenantId);
}
