package com.districore.platform.claim;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClaimResponse {
    private String id;
    private ClaimType type;
    private ClaimStatus status;
    private String orderId;
    private String reason;
}
