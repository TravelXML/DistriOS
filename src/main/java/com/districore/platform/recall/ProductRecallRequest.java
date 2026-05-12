package com.districore.platform.recall;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductRecallRequest {
    @NotBlank
    private String productId;
    private String reason;
}
