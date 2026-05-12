package com.districore.platform.product;

import com.districore.platform.common.TenantContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UomService {
    private final UomRepository repository;

    public UomService(UomRepository repository) {
        this.repository = repository;
    }

    public UomResponse createUom(UomRequest request) {
        Uom uom = new Uom();
        uom.setCode(request.getCode());
        uom.setDescription(request.getDescription());
        uom.setTenantId(TenantContext.getTenantId());
        repository.save(uom);
        return new UomResponse(uom.getId().toString(), uom.getCode(), uom.getDescription());
    }

    public List<UomResponse> listUoms() {
        return repository.findByTenantId(TenantContext.getTenantId()).stream()
                .map(uom -> new UomResponse(uom.getId().toString(), uom.getCode(), uom.getDescription()))
                .collect(Collectors.toList());
    }
}
