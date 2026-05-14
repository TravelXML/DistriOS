package com.districore.platform.retailer;

import com.districore.platform.common.ApiResponse;
import com.districore.platform.loyalty.LoyaltyService;
import com.districore.platform.order.OrderResponse;
import com.districore.platform.scheme.SchemeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/retailers")
public class RetailerController {
    private final RetailerService service;
    private final LoyaltyService loyaltyService;

    public RetailerController(RetailerService service, LoyaltyService loyaltyService) {
        this.service = service;
        this.loyaltyService = loyaltyService;
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

    @GetMapping("/{id}/orders")
    public ResponseEntity<ApiResponse<List<OrderResponse>>> orders(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.<List<OrderResponse>>builder()
                .success(true)
                .message("Retailer orders retrieved")
                .data(service.listOrders(id))
                .build());
    }

    @GetMapping("/{id}/schemes")
    public ResponseEntity<ApiResponse<List<SchemeResponse>>> schemes(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.<List<SchemeResponse>>builder()
                .success(true)
                .message("Retailer eligible schemes retrieved")
                .data(service.listEligibleSchemes(id))
                .build());
    }

    @GetMapping("/{id}/loyalty")
    public ResponseEntity<ApiResponse<com.districore.platform.loyalty.LoyaltyResponse>> loyalty(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.<com.districore.platform.loyalty.LoyaltyResponse>builder()
                .success(true)
                .message("Retailer loyalty summary retrieved")
                .data(loyaltyService.getAccount(id.toString()))
                .build());
    }
}
