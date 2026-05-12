package com.districore.platform.pricing;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PriceListItemRequest {
    @NotBlank
    private String productId;
    @NotNull
    private BigDecimal price;
    private String priceType;
}
