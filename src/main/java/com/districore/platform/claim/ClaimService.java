package com.districore.platform.claim;

import com.districore.platform.common.ResourceNotFoundException;
import com.districore.platform.common.TenantContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClaimService {
    private final ClaimRepository repository;

    public ClaimService(ClaimRepository repository) {
        this.repository = repository;
    }

    public ClaimResponse submitClaim(ClaimRequest request) {
        Claim claim = new Claim();
        claim.setType(request.getType());
        claim.setStatus(ClaimStatus.OPEN);
        claim.setOrderId(request.getOrderId());
        claim.setReason(request.getReason());
        claim.setTenantId(TenantContext.getTenantId());
        repository.save(claim);
        return toResponse(claim);
    }

    public List<ClaimResponse> listClaims() {
        return repository.findByTenantId(TenantContext.getTenantId()).stream().map(this::toResponse).collect(Collectors.toList());
    }

    public ClaimResponse getClaim(UUID id) {
        return toResponse(repository.findByIdAndTenantId(id, TenantContext.getTenantId()).orElseThrow(() -> new ResourceNotFoundException("Claim not found")));
    }

    public ClaimResponse approve(UUID id) {
        Claim claim = find(id);
        claim.setStatus(ClaimStatus.APPROVED);
        return toResponse(claim);
    }

    public ClaimResponse reject(UUID id) {
        Claim claim = find(id);
        claim.setStatus(ClaimStatus.REJECTED);
        return toResponse(claim);
    }

    private Claim find(UUID id) {
        return repository.findByIdAndTenantId(id, TenantContext.getTenantId()).orElseThrow(() -> new ResourceNotFoundException("Claim not found"));
    }

    private ClaimResponse toResponse(Claim claim) {
        return new ClaimResponse(claim.getId().toString(), claim.getType(), claim.getStatus(), claim.getOrderId(), claim.getReason());
    }
}
