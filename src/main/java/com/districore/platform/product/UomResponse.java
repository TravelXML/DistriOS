package com.districore.platform.product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UomResponse {
    private String id;
    private String code;
    private String description;
}
