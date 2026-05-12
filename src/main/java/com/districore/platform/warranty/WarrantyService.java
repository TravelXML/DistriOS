package com.districore.platform.warranty;

import com.districore.platform.common.ResourceNotFoundException;
import com.districore.platform.common.TenantContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class WarrantyService {
    private final WarrantyRepository repository;

    public WarrantyService(WarrantyRepository repository) {
        this.repository = repository;
    }

    public WarrantyResponse createWarranty(WarrantyRequest request) {
        Warranty warranty = new Warranty();
        warranty.setSerialNumber(request.getSerialNumber());
        warranty.setProductId(request.getProductId());
        warranty.setValidUntil(request.getValidUntil());
        warranty.setCustomerName(request.getCustomerName());
        warranty.setTenantId(TenantContext.getTenantId());
        repository.save(warranty);
        return toResponse(warranty);
    }

    public WarrantyResponse getWarranty(String serialNumber) {
        Warranty warranty = repository.findBySerialNumberAndTenantId(serialNumber, TenantContext.getTenantId()).orElseThrow(() -> new ResourceNotFoundException("Warranty not found"));
        return toResponse(warranty);
    }

    private WarrantyResponse toResponse(Warranty warranty) {
        return new WarrantyResponse(warranty.getId().toString(), warranty.getSerialNumber(), warranty.getProductId(), warranty.getValidUntil(), warranty.getCustomerName());
    }
}
