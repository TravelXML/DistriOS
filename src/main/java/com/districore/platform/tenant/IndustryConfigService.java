package com.districore.platform.tenant;

import com.districore.platform.common.ResourceNotFoundException;
import com.districore.platform.common.TenantContext;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class IndustryConfigService {
    private final IndustryConfigRepository repository;

    public IndustryConfigService(IndustryConfigRepository repository) {
        this.repository = repository;
    }

    public IndustryConfigResponse createConfig(IndustryConfigRequest request) {
        IndustryConfig entity = new IndustryConfig();
        entity.setIndustryVertical(request.getIndustryVertical());
        entity.setBatchRequired(request.isBatchRequired());
        entity.setExpiryRequired(request.isExpiryRequired());
        entity.setHsnMandatory(request.isHsnMandatory());
        entity.setGstMandatory(request.isGstMandatory());
        entity.setLicenseRequired(request.isLicenseRequired());
        entity.setTenantId(TenantContext.getTenantId());
        repository.save(entity);
        return toResponse(entity);
    }

    public List<IndustryConfigResponse> listConfigs() {
        String tenantId = TenantContext.getTenantId();
        return repository.findAll().stream()
                .filter(config -> tenantId.equals(config.getTenantId()))
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public IndustryConfigResponse getConfig(IndustryVertical vertical) {
        return repository.findByIndustryVerticalAndTenantId(vertical, TenantContext.getTenantId())
                .map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Industry config not found"));
    }

    public IndustryConfigResponse updateConfig(IndustryVertical vertical, IndustryConfigRequest request) {
        IndustryConfig config = repository.findByIndustryVerticalAndTenantId(vertical, TenantContext.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Industry config not found"));
        config.setBatchRequired(request.isBatchRequired());
        config.setExpiryRequired(request.isExpiryRequired());
        config.setHsnMandatory(request.isHsnMandatory());
        config.setGstMandatory(request.isGstMandatory());
        config.setLicenseRequired(request.isLicenseRequired());
        repository.save(config);
        return toResponse(config);
    }

    private IndustryConfigResponse toResponse(IndustryConfig entity) {
        return new IndustryConfigResponse(entity.getId().toString(), entity.getIndustryVertical(),
                entity.isBatchRequired(), entity.isExpiryRequired(), entity.isHsnMandatory(), entity.isGstMandatory(), entity.isLicenseRequired());
    }
}
