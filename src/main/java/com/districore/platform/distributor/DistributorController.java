package com.districore.platform.distributor;

import com.districore.platform.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/distributors")
public class DistributorController {
    private final DistributorService service;

    public DistributorController(DistributorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DistributorResponse>> create(@Valid @RequestBody CreateDistributorRequest request) {
        return ResponseEntity.ok(ApiResponse.<DistributorResponse>builder()
                .success(true)
                .message("Distributor created")
                .data(service.createDistributor(request))
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DistributorResponse>>> list() {
        return ResponseEntity.ok(ApiResponse.<List<DistributorResponse>>builder()
                .success(true)
                .message("Distributors retrieved")
                .data(service.listDistributors())
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DistributorResponse>> get(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.<DistributorResponse>builder()
                .success(true)
                .message("Distributor retrieved")
                .data(service.getDistributor(id))
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DistributorResponse>> update(@PathVariable UUID id, @Valid @RequestBody UpdateDistributorRequest request) {
        return ResponseEntity.ok(ApiResponse.<DistributorResponse>builder()
                .success(true)
                .message("Distributor updated")
                .data(service.updateDistributor(id, request))
                .build());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<DistributorResponse>> updateStatus(@PathVariable UUID id, @RequestBody StatusUpdateRequest request) {
        return ResponseEntity.ok(ApiResponse.<DistributorResponse>builder()
                .success(true)
                .message("Distributor status updated")
                .data(service.updateStatus(id, request))
                .build());
    }

    @PostMapping("/{id}/branches")
    public ResponseEntity<ApiResponse<DistributorBranchResponse>> addBranch(@PathVariable UUID id, @Valid @RequestBody CreateBranchRequest request) {
        return ResponseEntity.ok(ApiResponse.<DistributorBranchResponse>builder()
                .success(true)
                .message("Branch added")
                .data(service.addBranch(id, request))
                .build());
    }

    @GetMapping("/{id}/branches")
    public ResponseEntity<ApiResponse<List<DistributorBranchResponse>>> listBranches(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.<List<DistributorBranchResponse>>builder()
                .success(true)
                .message("Distributor branches retrieved")
                .data(service.listBranches(id))
                .build());
    }

    @PostMapping("/{id}/credit-limit")
    public ResponseEntity<ApiResponse<DistributorResponse>> updateCreditLimit(@PathVariable UUID id, @Valid @RequestBody CreditLimitRequest request) {
        return ResponseEntity.ok(ApiResponse.<DistributorResponse>builder()
                .success(true)
                .message("Credit limit updated")
                .data(service.updateCreditLimit(id, request))
                .build());
    }

    @PostMapping("/{id}/drug-license")
    public ResponseEntity<ApiResponse<DistributorDrugLicenseResponse>> addLicense(@PathVariable UUID id, @Valid @RequestBody DrugLicenseRequest request) {
        return ResponseEntity.ok(ApiResponse.<DistributorDrugLicenseResponse>builder()
                .success(true)
                .message("Drug license recorded")
                .data(service.addDrugLicense(id, request))
                .build());
    }

    @GetMapping("/{id}/performance-summary")
    public ResponseEntity<ApiResponse<DistributorPerformanceSummary>> performance(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.<DistributorPerformanceSummary>builder()
                .success(true)
                .message("Distributor performance summary")
                .data(service.getPerformanceSummary(id))
                .build());
    }
}
