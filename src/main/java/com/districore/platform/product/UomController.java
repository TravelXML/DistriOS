package com.districore.platform.product;

import com.districore.platform.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/uoms")
public class UomController {
    private final UomService service;

    public UomController(UomService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UomResponse>> create(@Valid @RequestBody UomRequest request) {
        return ResponseEntity.ok(ApiResponse.<UomResponse>builder()
                .success(true)
                .message("UOM created")
                .data(service.createUom(request))
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UomResponse>>> list() {
        return ResponseEntity.ok(ApiResponse.<List<UomResponse>>builder()
                .success(true)
                .message("UOMs retrieved")
                .data(service.listUoms())
                .build());
    }
}
