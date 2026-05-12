package com.districore.platform.recall;

import com.districore.platform.common.ResourceNotFoundException;
import com.districore.platform.common.TenantContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductRecallService {
    private final ProductRecallRepository repository;

    public ProductRecallService(ProductRecallRepository repository) {
        this.repository = repository;
    }

    public ProductRecallResponse create(ProductRecallRequest request) {
        ProductRecall recall = new ProductRecall();
        recall.setProductId(request.getProductId());
        recall.setReason(request.getReason());
        recall.setTenantId(TenantContext.getTenantId());
        repository.save(recall);
        return toResponse(recall);
    }

    public List<ProductRecallResponse> list() {
        return repository.findByTenantId(TenantContext.getTenantId()).stream().map(this::toResponse).collect(Collectors.toList());
    }

    public ProductRecallResponse get(UUID id) {
        return toResponse(repository.findByIdAndTenantId(id, TenantContext.getTenantId()).orElseThrow(() -> new ResourceNotFoundException("Product recall not found")));
    }

    public ProductRecallResponse close(UUID id) {
        ProductRecall recall = repository.findByIdAndTenantId(id, TenantContext.getTenantId()).orElseThrow(() -> new ResourceNotFoundException("Product recall not found"));
        recall.setClosed(true);
        repository.save(recall);
        return toResponse(recall);
    }

    private ProductRecallResponse toResponse(ProductRecall recall) {
        return new ProductRecallResponse(recall.getId().toString(), recall.getProductId(), recall.getReason(), recall.isClosed());
    }
}
