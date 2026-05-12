package com.districore.platform.scheme;

import com.districore.platform.common.ResourceNotFoundException;
import com.districore.platform.common.TenantContext;
import com.districore.platform.order.OrderLineReq;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class SchemeService {
    private final SchemeRepository repository;

    public SchemeService(SchemeRepository repository) {
        this.repository = repository;
    }

    public SchemeResponse createScheme(SchemeRequest request) {
        Scheme scheme = new Scheme();
        scheme.setName(request.getName());
        scheme.setType(request.getType());
        scheme.setDiscountValue(request.getDiscountValue());
        scheme.setBuyQuantity(request.getBuyQuantity());
        scheme.setGetQuantity(request.getGetQuantity());
        scheme.setTargetCategory(request.getTargetCategory());
        scheme.setActive(true);
        scheme.setTenantId(TenantContext.getTenantId());
        repository.save(scheme);
        return new SchemeResponse(scheme.getId().toString(), scheme.getName(), scheme.getType(), scheme.isActive());
    }

    public List<SchemeResponse> listSchemes() {
        return repository.findByTenantId(TenantContext.getTenantId()).stream()
                .map(s -> new SchemeResponse(s.getId().toString(), s.getName(), s.getType(), s.isActive()))
                .collect(Collectors.toList());
    }

    public SchemeResponse getScheme(UUID id) {
        Scheme scheme = repository.findByIdAndTenantId(id, TenantContext.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Scheme not found"));
        return new SchemeResponse(scheme.getId().toString(), scheme.getName(), scheme.getType(), scheme.isActive());
    }

    public SchemeResponse updateScheme(UUID id, SchemeRequest request) {
        Scheme scheme = repository.findByIdAndTenantId(id, TenantContext.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Scheme not found"));
        scheme.setName(request.getName());
        scheme.setType(request.getType());
        scheme.setDiscountValue(request.getDiscountValue());
        scheme.setBuyQuantity(request.getBuyQuantity());
        scheme.setGetQuantity(request.getGetQuantity());
        scheme.setTargetCategory(request.getTargetCategory());
        repository.save(scheme);
        return new SchemeResponse(scheme.getId().toString(), scheme.getName(), scheme.getType(), scheme.isActive());
    }

    public SchemeResponse updateStatus(UUID id, StatusUpdateRequest request) {
        Scheme scheme = repository.findByIdAndTenantId(id, TenantContext.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Scheme not found"));
        scheme.setActive(request.isActive());
        repository.save(scheme);
        return new SchemeResponse(scheme.getId().toString(), scheme.getName(), scheme.getType(), scheme.isActive());
    }

    public SchemeSimulationResponse simulateScheme(SchemeSimulationRequest request) {
        BigDecimal orderValue = request.getItems().stream().map(item -> item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal discount = BigDecimal.ZERO;
        String applied = "NONE";
        for (Scheme scheme : repository.findByTenantId(TenantContext.getTenantId())) {
            if (scheme.isActive() && scheme.getType() == SchemeType.PERCENTAGE_DISCOUNT && scheme.getDiscountValue() != null) {
                discount = orderValue.multiply(scheme.getDiscountValue().divide(BigDecimal.valueOf(100)));
                applied = scheme.getName();
                break;
            }
        }
        BigDecimal finalValue = orderValue.subtract(discount);
        return new SchemeSimulationResponse(orderValue, discount, 0, finalValue, applied, "Applied first matching percentage discount scheme");
    }

    public List<SchemeResponse> eligibleSchemes(String retailerId) {
        return listSchemes();
    }
}
