package com.districore.platform.scheme;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class SchemeSimulationResponse {
    private BigDecimal originalOrderValue;
    private BigDecimal discountValue;
    private int freeItems;
    private BigDecimal finalOrderValue;
    private String appliedSchemes;
    private String explanation;
}
