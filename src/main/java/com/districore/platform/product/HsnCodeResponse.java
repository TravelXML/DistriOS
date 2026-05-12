package com.districore.platform.product;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class HsnCodeResponse {
    private String id;
    private String code;
    private String description;
    private BigDecimal gstRate;
}
