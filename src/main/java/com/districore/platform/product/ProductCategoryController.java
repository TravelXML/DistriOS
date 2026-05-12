package com.districore.platform.product;

import com.districore.platform.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product-categories")
public class ProductCategoryController {
    private final ProductCategoryService service;

    public ProductCategoryController(ProductCategoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductCategoryResponse>> create(@Valid @RequestBody ProductCategoryRequest request) {
        return ResponseEntity.ok(ApiResponse.<ProductCategoryResponse>builder()
                .success(true)
                .message("Product category created")
                .data(service.createCategory(request))
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductCategoryResponse>>> list() {
        return ResponseEntity.ok(ApiResponse.<List<ProductCategoryResponse>>builder()
                .success(true)
                .message("Categories retrieved")
                .data(service.listCategories())
                .build());
    }
}
