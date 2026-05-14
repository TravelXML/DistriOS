package com.districore.platform.loyalty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoyaltyRequest {
    @NotBlank
    private String retailerId;
    @Min(1)
    private int points;
    private String description;
}
