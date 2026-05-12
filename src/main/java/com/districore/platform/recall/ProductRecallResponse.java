package com.districore.platform.recall;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductRecallResponse {
    private String id;
    private String productId;
    private String reason;
    private boolean closed;
}
