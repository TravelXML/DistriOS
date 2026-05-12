package com.districore.platform.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderLineReq {
    @NotBlank
    private String productId;
    @NotNull
    private Integer quantity;
    @NotNull
    private BigDecimal unitPrice;
}
