package com.districore.platform.analytics;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SalesSummary {
    private double totalSales;
    private double totalOrders;
    private double averageOrderValue;
    private String period;
}