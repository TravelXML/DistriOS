package com.districore.platform.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor
public class InvoiceResponse {
    private String id;
    private String orderId;
    private String retailerId;
    private BigDecimal totalAmount;
    private InvoiceStatus status;
    private Instant generatedAt;
}
