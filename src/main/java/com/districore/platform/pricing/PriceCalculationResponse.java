package com.districore.platform.pricing;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PriceCalculationResponse {
    private String productId;
    private BigDecimal basePrice;
    private BigDecimal taxAmount;
    private BigDecimal finalPrice;
}
