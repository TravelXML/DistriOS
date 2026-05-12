package com.districore.platform.product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductCategoryResponse {
    private String id;
    private String name;
    private String parentCategory;
}
