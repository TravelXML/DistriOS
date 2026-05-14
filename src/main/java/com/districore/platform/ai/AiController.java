package com.districore.platform.ai;

import com.districore.platform.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ai")
public class AiController {
    private final AiService service;

    public AiController(AiService service) {
        this.service = service;
    }

    @GetMapping("/product-recommendations")
    public ResponseEntity<ApiResponse<List<ProductRecommendation>>> productRecommendations(@RequestParam String retailerId) {
        return ResponseEntity.ok(ApiResponse.<List<ProductRecommendation>>builder()
                .success(true)
                .message("Product recommendations retrieved")
                .data(service.getProductRecommendations(retailerId))
                .build());
    }

    @GetMapping("/outlet-pulse/{retailerId}")
    public ResponseEntity<ApiResponse<String>> outletPulse(@PathVariable String retailerId) {
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .message("Outlet pulse retrieved")
                .data(service.getOutletPulse(retailerId))
                .build());
    }

    @PostMapping("/route-optimization")
    public ResponseEntity<ApiResponse<String>> routeOptimization(@RequestBody Object request) {
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .message("Route optimization completed")
                .data(service.optimizeRoute(request))
                .build());
    }

    @PostMapping("/scheme-simulation")
    public ResponseEntity<ApiResponse<String>> schemeSimulation(@RequestBody Object request) {
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .message("Scheme simulation completed")
                .data(service.simulateScheme(request))
                .build());
    }

    @GetMapping("/sales-analyzer")
    public ResponseEntity<ApiResponse<String>> salesAnalyzer() {
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .message("Sales analysis retrieved")
                .data(service.analyzeSales())
                .build());
    }

    @PostMapping("/target-disaggregation")
    public ResponseEntity<ApiResponse<String>> targetDisaggregation(@RequestBody Object request) {
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .message("Target disaggregation completed")
                .data(service.disaggregateTarget(request))
                .build());
    }

    @GetMapping("/smart-nudges")
    public ResponseEntity<ApiResponse<List<String>>> smartNudges() {
        return ResponseEntity.ok(ApiResponse.<List<String>>builder()
                .success(true)
                .message("Smart nudges retrieved")
                .data(service.getSmartNudges())
                .build());
    }

    @GetMapping("/supervisor-insights")
    public ResponseEntity<ApiResponse<String>> supervisorInsights() {
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .message("Supervisor insights retrieved")
                .data(service.getSupervisorInsights())
                .build());
    }
}