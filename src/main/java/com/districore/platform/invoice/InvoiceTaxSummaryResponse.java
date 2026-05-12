package com.districore.platform.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class InvoiceTaxSummaryResponse {
    private String invoiceId;
    private BigDecimal taxableValue;
    private BigDecimal taxAmount;
}
