package com.districore.platform.pricing;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PriceListResponse {
    private String id;
    private String name;
    private String distributorId;
    private String retailerId;
    private String region;
}
