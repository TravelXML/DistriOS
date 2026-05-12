package com.districore.platform.retailer;

import com.districore.platform.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/retailers")
public class RetailerController {
    private final RetailerService service;

    public RetailerController(RetailerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RetailerResponse>> create(@Valid @RequestBody CreateRetailerRequest request) {
        return ResponseEntity.ok(ApiResponse.<RetailerResponse>builder()
                .success(true)
                .message("Retailer created")
                .data(service.createRetailer(request))
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<RetailerResponse>>> list() {
        return ResponseEntity.ok(ApiResponse.<List<RetailerResponse>>builder()
                .success(true)
                .message("Retailers retrieved")
                .data(service.listRetailers())
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RetailerResponse>> get(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.<RetailerResponse>builder()
                .success(true)
                .message("Retailer retrieved")
                .data(service.getRetailer(id))
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RetailerResponse>> update(@PathVariable UUID id, @Valid @RequestBody UpdateRetailerRequest request) {
        return ResponseEntity.ok(ApiResponse.<RetailerResponse>builder()
                .success(true)
                .message("Retailer updated")
                .data(service.updateRetailer(id, request))
                .build());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<RetailerResponse>> updateStatus(@PathVariable UUID id, @RequestBody StatusUpdateRequest request) {
        return ResponseEntity.ok(ApiResponse.<RetailerResponse>builder()
                .success(true)
                .message("Retailer status updated")
                .data(service.updateStatus(id, request))
                .build());
    }

    @PostMapping("/{id}/kyc")
    public ResponseEntity<ApiResponse<RetailerKycResponse>> addKyc(@PathVariable UUID id, @Valid @RequestBody RetailerKycRequest request) {
        return ResponseEntity.ok(ApiResponse.<RetailerKycResponse>builder()
                .success(true)
                .message("KYC recorded")
                .data(service.addKyc(id, request))
                .build());
    }

    @PostMapping("/{id}/drug-license")
    public ResponseEntity<ApiResponse<RetailerDrugLicenseResponse>> addDrugLicense(@PathVariable UUID id, @Valid @RequestBody RetailerDrugLicenseRequest request) {
        return ResponseEntity.ok(ApiResponse.<RetailerDrugLicenseResponse>builder()
                .success(true)
                .message("Drug license added")
                .data(service.addDrugLicense(id, request))
                .build());
    }

    @GetMapping("/{id}/ledger")
    public ResponseEntity<ApiResponse<RetailerLedgerResponse>> ledger(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.<RetailerLedgerResponse>builder()
                .success(true)
                .message("Retailer ledger")
                .data(service.getLedger(id))
                .build());
    }
}
