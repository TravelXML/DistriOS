package com.districore.platform.product;

import com.districore.platform.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> create(@Valid @RequestBody ProductRequest request) {
        return ResponseEntity.ok(ApiResponse.<ProductResponse>builder()
                .success(true)
                .message("Product created")
                .data(service.createProduct(request))
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> list() {
        return ResponseEntity.ok(ApiResponse.<List<ProductResponse>>builder()
                .success(true)
                .message("Products retrieved")
                .data(service.listProducts())
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> get(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.<ProductResponse>builder()
                .success(true)
                .message("Product retrieved")
                .data(service.getProduct(id))
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> update(@PathVariable UUID id, @Valid @RequestBody ProductRequest request) {
        return ResponseEntity.ok(ApiResponse.<ProductResponse>builder()
                .success(true)
                .message("Product updated")
                .data(service.updateProduct(id, request))
                .build());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<ProductResponse>> updateStatus(@PathVariable UUID id, @RequestBody StatusUpdateRequest request) {
        return ResponseEntity.ok(ApiResponse.<ProductResponse>builder()
                .success(true)
                .message("Product status updated")
                .data(service.updateStatus(id, request))
                .build());
    }

    @PostMapping("/{productId}/serial-numbers")
    public ResponseEntity<ApiResponse<ProductSerialNumberResponse>> addSerialNumber(@PathVariable UUID productId,
                                                                                     @Valid @RequestBody ProductSerialNumberRequest request) {
        return ResponseEntity.ok(ApiResponse.<ProductSerialNumberResponse>builder()
                .success(true)
                .message("Serial number added")
                .data(service.addSerialNumber(productId, request))
                .build());
    }

    @GetMapping("/{productId}/serial-numbers")
    public ResponseEntity<ApiResponse<java.util.List<ProductSerialNumberResponse>>> getSerialNumbers(@PathVariable UUID productId) {
        return ResponseEntity.ok(ApiResponse.<java.util.List<ProductSerialNumberResponse>>builder()
                .success(true)
                .message("Serial numbers retrieved")
                .data(service.listSerialNumbers(productId))
                .build());
    }
}
