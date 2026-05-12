package com.districore.platform.pricing;

import com.districore.platform.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/price-lists")
public class PriceListController {
    private final PricingService service;

    public PriceListController(PricingService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PriceListResponse>> create(@Valid @RequestBody PriceListRequest request) {
        return ResponseEntity.ok(ApiResponse.<PriceListResponse>builder()
                .success(true)
                .message("Price list created")
                .data(service.createPriceList(request))
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PriceListResponse>>> list() {
        return ResponseEntity.ok(ApiResponse.<List<PriceListResponse>>builder()
                .success(true)
                .message("Price lists retrieved")
                .data(service.listPriceLists())
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PriceListResponse>> get(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.<PriceListResponse>builder()
                .success(true)
                .message("Price list retrieved")
                .data(service.getPriceList(id))
                .build());
    }

    @PostMapping("/{id}/items")
    public ResponseEntity<ApiResponse<PriceListItemResponse>> addItem(@PathVariable UUID id, @Valid @RequestBody PriceListItemRequest request) {
        return ResponseEntity.ok(ApiResponse.<PriceListItemResponse>builder()
                .success(true)
                .message("Price list item added")
                .data(service.createPriceListItem(id, request))
                .build());
    }
}
