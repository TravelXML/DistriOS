package com.districore.platform.claim;

import com.districore.platform.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/claims")
public class ClaimController {
    private final ClaimService service;

    public ClaimController(ClaimService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ClaimResponse>> submit(@Valid @RequestBody ClaimRequest request) {
        return ResponseEntity.ok(ApiResponse.<ClaimResponse>builder()
                .success(true)
                .message("Claim submitted")
                .data(service.submitClaim(request))
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ClaimResponse>>> list() {
        return ResponseEntity.ok(ApiResponse.<List<ClaimResponse>>builder()
                .success(true)
                .message("Claims retrieved")
                .data(service.listClaims())
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ClaimResponse>> get(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.<ClaimResponse>builder()
                .success(true)
                .message("Claim retrieved")
                .data(service.getClaim(id))
                .build());
    }

    @PatchMapping("/{id}/approve")
    public ResponseEntity<ApiResponse<ClaimResponse>> approve(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.<ClaimResponse>builder()
                .success(true)
                .message("Claim approved")
                .data(service.approve(id))
                .build());
    }

    @PatchMapping("/{id}/reject")
    public ResponseEntity<ApiResponse<ClaimResponse>> reject(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.<ClaimResponse>builder()
                .success(true)
                .message("Claim rejected")
                .data(service.reject(id))
                .build());
    }
}
