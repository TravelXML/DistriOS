package com.districore.platform.product;

import com.districore.platform.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandController {
    private final BrandService service;

    public BrandController(BrandService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BrandResponse>> create(@Valid @RequestBody BrandRequest request) {
        return ResponseEntity.ok(ApiResponse.<BrandResponse>builder()
                .success(true)
                .message("Brand created")
                .data(service.createBrand(request))
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<BrandResponse>>> list() {
        return ResponseEntity.ok(ApiResponse.<List<BrandResponse>>builder()
                .success(true)
                .message("Brands retrieved")
                .data(service.listBrands())
                .build());
    }
}
