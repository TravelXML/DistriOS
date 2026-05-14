package com.districore.platform.retailer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RetailerLoyaltyResponse {
    private String retailerId;
    private int pointsBalance;
    private String tier;
    private int redeemablePoints;
}
