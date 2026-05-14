package com.districore.platform.claim;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClaimRequest {
    @NotNull
    private ClaimType type;
    @NotBlank
    private String orderId;
    @NotBlank
    private String reason;
    private String idempotencyKey;
}
