package com.districore.platform.product;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductCategoryRequest {
    @NotBlank
    private String name;
    private String parentCategory;
}
