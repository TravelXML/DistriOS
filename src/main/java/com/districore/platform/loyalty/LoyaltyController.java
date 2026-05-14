package com.districore.platform.loyalty;

import com.districore.platform.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/loyalty")
public class LoyaltyController {
    private final LoyaltyService service;

    public LoyaltyController(LoyaltyService service) {
        this.service = service;
    }

    @PostMapping("/accounts")
    public ResponseEntity<ApiResponse<LoyaltyResponse>> createAccount(@Valid @RequestBody LoyaltyRequest request) {
        return ResponseEntity.ok(ApiResponse.<LoyaltyResponse>builder()
                .success(true)
                .message("Loyalty account created")
                .data(service.earnPoints(request))
                .build());
    }

    @GetMapping("/accounts/{retailerId}")
    public ResponseEntity<ApiResponse<LoyaltyResponse>> getAccount(@PathVariable String retailerId) {
        return ResponseEntity.ok(ApiResponse.<LoyaltyResponse>builder()
                .success(true)
                .message("Loyalty account retrieved")
                .data(service.getAccount(retailerId))
                .build());
    }

    @PostMapping("/earn")
    public ResponseEntity<ApiResponse<LoyaltyResponse>> earnPoints(@Valid @RequestBody LoyaltyRequest request) {
        return ResponseEntity.ok(ApiResponse.<LoyaltyResponse>builder()
                .success(true)
                .message("Points earned")
                .data(service.earnPoints(request))
                .build());
    }

    @PostMapping("/redeem")
    public ResponseEntity<ApiResponse<LoyaltyResponse>> redeemPoints(@Valid @RequestBody LoyaltyRequest request) {
        return ResponseEntity.ok(ApiResponse.<LoyaltyResponse>builder()
                .success(true)
                .message("Points redeemed")
                .data(service.redeemPoints(request))
                .build());
    }

    @GetMapping("/transactions/{retailerId}")
    public ResponseEntity<ApiResponse<List<LoyaltyTransactionResponse>>> listTransactions(@PathVariable String retailerId) {
        return ResponseEntity.ok(ApiResponse.<List<LoyaltyTransactionResponse>>builder()
                .success(true)
                .message("Loyalty transactions retrieved")
                .data(service.listTransactions(retailerId))
                .build());
    }
}
