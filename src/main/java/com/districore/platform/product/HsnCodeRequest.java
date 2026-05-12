package com.districore.platform.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class HsnCodeRequest {
    @NotBlank
    private String code;
    private String description;
    @NotNull
    private BigDecimal gstRate;
}
