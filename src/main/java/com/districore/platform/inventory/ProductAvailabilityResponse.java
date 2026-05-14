package com.districore.platform.inventory;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductAvailabilityResponse {
    private String productId;
    private BigDecimal totalIn;
    private BigDecimal totalOut;
    private BigDecimal available;
}
