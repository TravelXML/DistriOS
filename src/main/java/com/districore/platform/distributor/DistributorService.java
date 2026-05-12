package com.districore.platform.distributor;

import com.districore.platform.common.ResourceNotFoundException;
import com.districore.platform.common.TenantContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class DistributorService {
    private final DistributorRepository repository;
    private final DistributorBranchRepository branchRepository;
    private final DistributorDrugLicenseRepository licenseRepository;

    public DistributorService(DistributorRepository repository,
                              DistributorBranchRepository branchRepository,
                              DistributorDrugLicenseRepository licenseRepository) {
        this.repository = repository;
        this.branchRepository = branchRepository;
        this.licenseRepository = licenseRepository;
    }

    public DistributorResponse createDistributor(CreateDistributorRequest request) {
        Distributor distributor = new Distributor();
        distributor.setName(request.getName());
        distributor.setEmail(request.getEmail());
        distributor.setPhone(request.getPhone());
        distributor.setTerritory(request.getTerritory());
        distributor.setStatus(DistributorStatus.ACTIVE);
        distributor.setCreditLimit(BigDecimal.ZERO);
        distributor.setTenantId(TenantContext.getTenantId());
        repository.save(distributor);
        return toResponse(distributor);
    }

    public List<DistributorResponse> listDistributors() {
        return repository.findByTenantId(TenantContext.getTenantId()).stream().map(this::toResponse).collect(Collectors.toList());
    }

    public DistributorResponse getDistributor(UUID id) {
        return toResponse(findById(id));
    }

    public DistributorResponse updateDistributor(UUID id, UpdateDistributorRequest request) {
        Distributor distributor = findById(id);
        distributor.setName(request.getName());
        distributor.setEmail(request.getEmail());
        distributor.setPhone(request.getPhone());
        distributor.setTerritory(request.getTerritory());
        repository.save(distributor);
        return toResponse(distributor);
    }

    public DistributorResponse updateStatus(UUID id, StatusUpdateRequest request) {
        Distributor distributor = findById(id);
        distributor.setStatus(request.getStatus());
        repository.save(distributor);
        return toResponse(distributor);
    }

    public DistributorBranchResponse addBranch(UUID id, CreateBranchRequest request) {
        Distributor distributor = findById(id);
        DistributorBranch branch = new DistributorBranch();
        branch.setBranchName(request.getBranchName());
        branch.setLocation(request.getLocation());
        branch.setManagerName(request.getManagerName());
        branch.setPhone(request.getPhone());
        branch.setDistributor(distributor);
        branch.setTenantId(TenantContext.getTenantId());
        branchRepository.save(branch);
        return new DistributorBranchResponse(branch.getId().toString(), branch.getBranchName(), branch.getLocation(), branch.getManagerName(), branch.getPhone());
    }

    public List<DistributorBranchResponse> listBranches(UUID id) {
        return branchRepository.findByDistributorIdAndTenantId(id, TenantContext.getTenantId()).stream()
                .map(branch -> new DistributorBranchResponse(branch.getId().toString(), branch.getBranchName(), branch.getLocation(), branch.getManagerName(), branch.getPhone()))
                .collect(Collectors.toList());
    }

    public DistributorResponse updateCreditLimit(UUID id, CreditLimitRequest request) {
        Distributor distributor = findById(id);
        distributor.setCreditLimit(request.getCreditLimit());
        repository.save(distributor);
        return toResponse(distributor);
    }

    public DistributorDrugLicenseResponse addDrugLicense(UUID id, DrugLicenseRequest request) {
        Distributor distributor = findById(id);
        DistributorDrugLicense license = new DistributorDrugLicense();
        license.setLicenseNumber(request.getLicenseNumber());
        license.setValidFrom(request.getValidFrom());
        license.setValidUntil(request.getValidUntil());
        license.setDocumentUrl(request.getDocumentUrl());
        license.setDistributor(distributor);
        license.setTenantId(TenantContext.getTenantId());
        licenseRepository.save(license);
        return new DistributorDrugLicenseResponse(license.getId().toString(), license.getLicenseNumber(), license.getValidFrom(), license.getValidUntil(), license.getDocumentUrl());
    }

    public DistributorPerformanceSummary getPerformanceSummary(UUID id) {
        Distributor distributor = findById(id);
        return new DistributorPerformanceSummary(distributor.getId().toString(), distributor.getName(), distributor.getTerritory(), distributor.getStatus().name(), distributor.getCreditLimit());
    }

    private Distributor findById(UUID id) {
        return repository.findByIdAndTenantId(id, TenantContext.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Distributor not found"));
    }

    private DistributorResponse toResponse(Distributor distributor) {
        return new DistributorResponse(distributor.getId().toString(), distributor.getName(), distributor.getEmail(), distributor.getPhone(), distributor.getTerritory(), distributor.getStatus(), distributor.getCreditLimit());
    }
}
