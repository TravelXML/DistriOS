package com.districore.platform.retailer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RetailerLedgerResponse {
    private String retailerId;
    private String retailerName;
    private int outstandingInvoices;
    private int duePayments;
}
