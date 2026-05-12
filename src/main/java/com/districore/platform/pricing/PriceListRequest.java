package com.districore.platform.pricing;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PriceListRequest {
    @NotBlank
    private String name;
    private String distributorId;
    private String retailerId;
    private String region;
}
