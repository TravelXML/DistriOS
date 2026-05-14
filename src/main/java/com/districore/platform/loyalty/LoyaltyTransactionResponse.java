package com.districore.platform.loyalty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoyaltyTransactionResponse {
    private String id;
    private String type;
    private int points;
    private String description;
}
