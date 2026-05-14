package com.districore.platform.retailerapp;

import com.districore.platform.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/retailer-app")
public class RetailerAppController {
    private final RetailerAppService service;

    public RetailerAppController(RetailerAppService service) {
        this.service = service;
    }

    @GetMapping("/home")
    public ResponseEntity<ApiResponse<HomeData>> home() {
        return ResponseEntity.ok(ApiResponse.<HomeData>builder()
                .success(true)
                .message("Home data retrieved")
                .data(service.getHomeData())
                .build());
    }

    @GetMapping("/products")
    public ResponseEntity<ApiResponse<List<Object>>> products() {
        return ResponseEntity.ok(ApiResponse.<List<Object>>builder()
                .success(true)
                .message("Products retrieved")
                .data(service.getProducts())
                .build());
    }

    @GetMapping("/recommended-products")
    public ResponseEntity<ApiResponse<List<Object>>> recommendedProducts() {
        return ResponseEntity.ok(ApiResponse.<List<Object>>builder()
                .success(true)
                .message("Recommended products retrieved")
                .data(service.getRecommendedProducts())
                .build());
    }

    @GetMapping("/schemes")
    public ResponseEntity<ApiResponse<List<Object>>> schemes() {
        return ResponseEntity.ok(ApiResponse.<List<Object>>builder()
                .success(true)
                .message("Applicable schemes retrieved")
                .data(service.getSchemes())
                .build());
    }

    @PostMapping("/cart/items")
    public ResponseEntity<ApiResponse<String>> addToCart(@RequestBody Object item) {
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .message("Item added to cart")
                .data(service.addToCart(item))
                .build());
    }

    @GetMapping("/cart")
    public ResponseEntity<ApiResponse<Object>> getCart() {
        return ResponseEntity.ok(ApiResponse.<Object>builder()
                .success(true)
                .message("Cart retrieved")
                .data(service.getCart())
                .build());
    }

    @PostMapping("/orders")
    public ResponseEntity<ApiResponse<String>> placeOrder(@RequestBody Object order) {
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .message("Order placed")
                .data(service.placeOrder(order))
                .build());
    }

    @GetMapping("/orders")
    public ResponseEntity<ApiResponse<List<Object>>> orders() {
        return ResponseEntity.ok(ApiResponse.<List<Object>>builder()
                .success(true)
                .message("Orders retrieved")
                .data(service.getOrders())
                .build());
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<ApiResponse<Object>> order(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.<Object>builder()
                .success(true)
                .message("Order details retrieved")
                .data(service.getOrder(id))
                .build());
    }

    @PostMapping("/complaints")
    public ResponseEntity<ApiResponse<String>> submitComplaint(@RequestBody Object complaint) {
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .message("Complaint submitted")
                .data(service.submitComplaint(complaint))
                .build());
    }

    @GetMapping("/loyalty")
    public ResponseEntity<ApiResponse<Object>> loyalty() {
        return ResponseEntity.ok(ApiResponse.<Object>builder()
                .success(true)
                .message("Loyalty summary retrieved")
                .data(service.getLoyaltySummary())
                .build());
    }
}