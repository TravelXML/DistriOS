package com.districore.platform.scheme;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SchemeRequest {
    @NotBlank
    private String name;
    @NotNull
    private SchemeType type;
    private BigDecimal discountValue;
    private Integer buyQuantity;
    private Integer getQuantity;
    private String targetCategory;
}
