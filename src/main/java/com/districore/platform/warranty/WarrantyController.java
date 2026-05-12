package com.districore.platform.warranty;

import com.districore.platform.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/warranties")
public class WarrantyController {
    private final WarrantyService service;

    public WarrantyController(WarrantyService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<WarrantyResponse>> create(@Valid @RequestBody WarrantyRequest request) {
        return ResponseEntity.ok(ApiResponse.<WarrantyResponse>builder()
                .success(true)
                .message("Warranty created")
                .data(service.createWarranty(request))
                .build());
    }

    @GetMapping("/{serialNumber}")
    public ResponseEntity<ApiResponse<WarrantyResponse>> get(@PathVariable String serialNumber) {
        return ResponseEntity.ok(ApiResponse.<WarrantyResponse>builder()
                .success(true)
                .message("Warranty retrieved")
                .data(service.getWarranty(serialNumber))
                .build());
    }
}
