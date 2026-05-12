package com.districore.platform.product;

import com.districore.platform.common.TenantContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductCategoryService {
    private final ProductCategoryRepository repository;

    public ProductCategoryService(ProductCategoryRepository repository) {
        this.repository = repository;
    }

    public ProductCategoryResponse createCategory(ProductCategoryRequest request) {
        ProductCategory category = new ProductCategory();
        category.setName(request.getName());
        category.setParentCategory(request.getParentCategory());
        category.setTenantId(TenantContext.getTenantId());
        repository.save(category);
        return new ProductCategoryResponse(category.getId().toString(), category.getName(), category.getParentCategory());
    }

    public List<ProductCategoryResponse> listCategories() {
        return repository.findByTenantId(TenantContext.getTenantId()).stream()
                .map(category -> new ProductCategoryResponse(category.getId().toString(), category.getName(), category.getParentCategory()))
                .collect(Collectors.toList());
    }
}
