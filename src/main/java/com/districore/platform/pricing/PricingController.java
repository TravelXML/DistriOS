package com.districore.platform.pricing;

import com.districore.platform.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PricingController {
    private final PricingService service;

    public PricingController(PricingService service) {
        this.service = service;
    }

    @GetMapping("/products/{productId}/prices")
    public ResponseEntity<ApiResponse<List<PriceListItemResponse>>> productPrices(@PathVariable String productId) {
        return ResponseEntity.ok(ApiResponse.<List<PriceListItemResponse>>builder()
                .success(true)
                .message("Product prices retrieved")
                .data(service.getProductPrices(productId))
                .build());
    }

    @PostMapping("/pricing/calculate")
    public ResponseEntity<ApiResponse<PriceCalculationResponse>> calculate(@RequestBody PricingCalculateRequest request) {
        return ResponseEntity.ok(ApiResponse.<PriceCalculationResponse>builder()
                .success(true)
                .message("Price calculated")
                .data(service.calculatePrice(request))
                .build());
    }
}
