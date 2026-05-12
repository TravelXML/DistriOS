package com.districore.platform.product;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductResponse {
    private String id;
    private String skuCode;
    private String productName;
    private String brand;
    private String category;
    private String uom;
    private Integer packSize;
    private String packSizeUom;
    private Integer caseSize;
    private String barcode;
    private String hsnCode;
    private BigDecimal gstRate;
    private BigDecimal mrp;
    private BigDecimal basePrice;
    private ProductStatus status;
}
