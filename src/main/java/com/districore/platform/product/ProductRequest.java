package com.districore.platform.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {
    @NotBlank
    private String skuCode;
    @NotBlank
    private String productName;
    @NotBlank
    private String brandId;
    @NotBlank
    private String categoryId;
    @NotBlank
    private String uomId;
    @NotBlank
    private String hsnCodeId;

    private Integer packSize;
    private String packSizeUom;
    private Integer caseSize;
    private String barcode;
    @NotNull
    private BigDecimal gstRate;
    @NotNull
    private BigDecimal mrp;
    @NotNull
    private BigDecimal basePrice;
    private boolean batchRequired;
    private boolean expiryRequired;
    private boolean serialNumberRequired;
    private boolean warrantyRequired;
    private String composition;
    private String manufacturerName;
    private Integer shelfLifeDays;
    private Integer warrantyMonths;
    private String regulatoryCategory;
}
