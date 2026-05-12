package com.districore.platform.recall;

import com.districore.platform.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product-recalls")
public class ProductRecallController {
    private final ProductRecallService service;

    public ProductRecallController(ProductRecallService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductRecallResponse>> create(@Valid @RequestBody ProductRecallRequest request) {
        return ResponseEntity.ok(ApiResponse.<ProductRecallResponse>builder()
                .success(true)
                .message("Product recall created")
                .data(service.create(request))
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductRecallResponse>>> list() {
        return ResponseEntity.ok(ApiResponse.<List<ProductRecallResponse>>builder()
                .success(true)
                .message("Product recalls retrieved")
                .data(service.list())
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductRecallResponse>> get(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.<ProductRecallResponse>builder()
                .success(true)
                .message("Product recall retrieved")
                .data(service.get(id))
                .build());
    }

    @PatchMapping("/{id}/close")
    public ResponseEntity<ApiResponse<ProductRecallResponse>> close(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.<ProductRecallResponse>builder()
                .success(true)
                .message("Product recall closed")
                .data(service.close(id))
                .build());
    }
}
