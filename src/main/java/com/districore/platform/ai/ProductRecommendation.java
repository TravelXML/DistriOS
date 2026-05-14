package com.districore.platform.ai;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductRecommendation {
    private String productId;
    private String productName;
    private String reason;
    private double confidence;
}