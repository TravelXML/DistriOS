package com.districore.platform.product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductSerialNumberResponse {
    private String id;
    private String serialNumber;
    private String productId;
}
