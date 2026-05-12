package com.districore.platform.pricing;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PriceListItemResponse {
    private String id;
    private String priceListId;
    private String productId;
    private BigDecimal price;
    private String priceType;
}
