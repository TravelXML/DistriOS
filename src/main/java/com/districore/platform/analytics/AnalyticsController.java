package com.districore.platform.analytics;

import com.districore.platform.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/analytics")
public class AnalyticsController {
    private final AnalyticsService service;

    public AnalyticsController(AnalyticsService service) {
        this.service = service;
    }

    @GetMapping("/sales-summary")
    public ResponseEntity<ApiResponse<SalesSummary>> salesSummary() {
        return ResponseEntity.ok(ApiResponse.<SalesSummary>builder()
                .success(true)
                .message("Sales summary retrieved")
                .data(service.getSalesSummary())
                .build());
    }

    @GetMapping("/distributor-performance")
    public ResponseEntity<ApiResponse<String>> distributorPerformance() {
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .message("Distributor performance retrieved")
                .data(service.getDistributorPerformance())
                .build());
    }

    @GetMapping("/retailer-performance")
    public ResponseEntity<ApiResponse<String>> retailerPerformance() {
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .message("Retailer performance retrieved")
                .data(service.getRetailerPerformance())
                .build());
    }

    @GetMapping("/product-performance")
    public ResponseEntity<ApiResponse<String>> productPerformance() {
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .message("Product performance retrieved")
                .data(service.getProductPerformance())
                .build());
    }

    @GetMapping("/scheme-performance")
    public ResponseEntity<ApiResponse<String>> schemePerformance() {
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .message("Scheme performance retrieved")
                .data(service.getSchemePerformance())
                .build());
    }

    @GetMapping("/stock-movement")
    public ResponseEntity<ApiResponse<String>> stockMovement() {
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .message("Stock movement analysis retrieved")
                .data(service.getStockMovement())
                .build());
    }

    @GetMapping("/sfa-productivity")
    public ResponseEntity<ApiResponse<String>> sfaProductivity() {
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .message("SFA productivity analysis retrieved")
                .data(service.getSfaProductivity())
                .build());
    }

    @GetMapping("/claims-summary")
    public ResponseEntity<ApiResponse<String>> claimsSummary() {
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .message("Claims summary retrieved")
                .data(service.getClaimsSummary())
                .build());
    }
}