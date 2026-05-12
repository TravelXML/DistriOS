package com.districore.platform.product;

import com.districore.platform.common.TenantContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BrandService {
    private final BrandRepository repository;

    public BrandService(BrandRepository repository) {
        this.repository = repository;
    }

    public BrandResponse createBrand(BrandRequest request) {
        Brand brand = new Brand();
        brand.setName(request.getName());
        brand.setTenantId(TenantContext.getTenantId());
        repository.save(brand);
        return new BrandResponse(brand.getId().toString(), brand.getName());
    }

    public List<BrandResponse> listBrands() {
        return repository.findByTenantId(TenantContext.getTenantId()).stream()
                .map(brand -> new BrandResponse(brand.getId().toString(), brand.getName()))
                .collect(Collectors.toList());
    }
}
