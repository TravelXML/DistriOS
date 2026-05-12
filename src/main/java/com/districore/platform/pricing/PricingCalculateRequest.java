package com.districore.platform.pricing;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PricingCalculateRequest {
    @NotBlank
    private String productId;
    private int quantity = 1;
}
