package com.districore.platform.scheme;

import com.districore.platform.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/schemes")
public class SchemeController {
    private final SchemeService service;

    public SchemeController(SchemeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<SchemeResponse>> create(@Valid @RequestBody SchemeRequest request) {
        return ResponseEntity.ok(ApiResponse.<SchemeResponse>builder()
                .success(true)
                .message("Scheme created")
                .data(service.createScheme(request))
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<SchemeResponse>>> list() {
        return ResponseEntity.ok(ApiResponse.<List<SchemeResponse>>builder()
                .success(true)
                .message("Schemes retrieved")
                .data(service.listSchemes())
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SchemeResponse>> get(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.<SchemeResponse>builder()
                .success(true)
                .message("Scheme retrieved")
                .data(service.getScheme(id))
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SchemeResponse>> update(@PathVariable UUID id, @Valid @RequestBody SchemeRequest request) {
        return ResponseEntity.ok(ApiResponse.<SchemeResponse>builder()
                .success(true)
                .message("Scheme updated")
                .data(service.updateScheme(id, request))
                .build());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<SchemeResponse>> updateStatus(@PathVariable UUID id, @RequestBody StatusUpdateRequest request) {
        return ResponseEntity.ok(ApiResponse.<SchemeResponse>builder()
                .success(true)
                .message("Scheme status updated")
                .data(service.updateStatus(id, request))
                .build());
    }

    @PostMapping("/simulate")
    public ResponseEntity<ApiResponse<SchemeSimulationResponse>> simulate(@Valid @RequestBody SchemeSimulationRequest request) {
        return ResponseEntity.ok(ApiResponse.<SchemeSimulationResponse>builder()
                .success(true)
                .message("Scheme simulation completed")
                .data(service.simulateScheme(request))
                .build());
    }

    @PostMapping("/retailers/{retailerId}/eligible-schemes")
    public ResponseEntity<ApiResponse<List<SchemeResponse>>> eligible(@PathVariable String retailerId) {
        return ResponseEntity.ok(ApiResponse.<List<SchemeResponse>>builder()
                .success(true)
                .message("Eligible schemes retrieved")
                .data(service.eligibleSchemes(retailerId))
                .build());
    }
}
