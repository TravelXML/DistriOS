package com.districore.platform.tenant;

import com.districore.platform.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/industry-config")
public class IndustryConfigController {
    private final IndustryConfigService service;

    public IndustryConfigController(IndustryConfigService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<IndustryConfigResponse>> create(@Valid @RequestBody IndustryConfigRequest request) {
        return ResponseEntity.ok(ApiResponse.<IndustryConfigResponse>builder()
                .success(true)
                .message("Industry config created")
                .data(service.createConfig(request))
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<IndustryConfigResponse>>> list() {
        return ResponseEntity.ok(ApiResponse.<List<IndustryConfigResponse>>builder()
                .success(true)
                .message("Industry configs retrieved")
                .data(service.listConfigs())
                .build());
    }

    @GetMapping("/{industryVertical}")
    public ResponseEntity<ApiResponse<IndustryConfigResponse>> get(@PathVariable IndustryVertical industryVertical) {
        return ResponseEntity.ok(ApiResponse.<IndustryConfigResponse>builder()
                .success(true)
                .message("Industry config retrieved")
                .data(service.getConfig(industryVertical))
                .build());
    }

    @PutMapping("/{industryVertical}")
    public ResponseEntity<ApiResponse<IndustryConfigResponse>> update(@PathVariable IndustryVertical industryVertical,
                                                                      @Valid @RequestBody IndustryConfigRequest request) {
        return ResponseEntity.ok(ApiResponse.<IndustryConfigResponse>builder()
                .success(true)
                .message("Industry config updated")
                .data(service.updateConfig(industryVertical, request))
                .build());
    }
}
