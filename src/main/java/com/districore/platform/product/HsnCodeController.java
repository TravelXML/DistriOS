package com.districore.platform.product;

import com.districore.platform.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/hsn-codes")
public class HsnCodeController {
    private final HsnCodeService service;

    public HsnCodeController(HsnCodeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<HsnCodeResponse>> create(@Valid @RequestBody HsnCodeRequest request) {
        return ResponseEntity.ok(ApiResponse.<HsnCodeResponse>builder()
                .success(true)
                .message("HSN code created")
                .data(service.createHsnCode(request))
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<HsnCodeResponse>>> list() {
        return ResponseEntity.ok(ApiResponse.<List<HsnCodeResponse>>builder()
                .success(true)
                .message("HSN codes retrieved")
                .data(service.listHsnCodes())
                .build());
    }
}
