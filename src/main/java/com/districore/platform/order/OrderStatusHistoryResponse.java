package com.districore.platform.order;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class OrderStatusHistoryResponse {
    private String status;
    private Instant timestamp;
}
