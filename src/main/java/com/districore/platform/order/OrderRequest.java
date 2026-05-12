package com.districore.platform.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    @NotBlank
    private String retailerId;
    @NotBlank
    private String distributorId;
    @NotEmpty
    private List<OrderLineReq> items;
    private OrderSource source = OrderSource.ADMIN;
    private String idempotencyKey;
}
