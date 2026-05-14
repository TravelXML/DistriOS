package com.districore.platform.loyalty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoyaltyResponse {
    private String retailerId;
    private int pointsBalance;
    private String tier;
}
