package com.districore.platform.product;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductSerialNumberRequest {
    @NotBlank
    private String serialNumber;
}
