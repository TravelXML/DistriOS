package com.districore.platform.retailerapp;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class HomeData {
    private String retailerName;
    private double outstandingAmount;
    private int pendingOrders;
    private List<String> recentSchemes;
}