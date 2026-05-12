package com.districore.platform.order;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderResponse {
    private String id;
    private String retailerId;
    private String distributorId;
    private OrderStatus status;
    private OrderSource source;
    private List<OrderLineResponse> items;
}
