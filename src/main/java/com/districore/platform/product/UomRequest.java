package com.districore.platform.product;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UomRequest {
    @NotBlank
    private String code;
    private String description;
}
