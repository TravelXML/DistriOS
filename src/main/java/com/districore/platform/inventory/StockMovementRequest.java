package com.districore.platform.inventory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class StockMovementRequest {
    @NotBlank
    private String warehouseId;
    @NotBlank
    private String productId;
    @NotNull
    private BigDecimal quantity;
    @NotNull
    private InventoryStockType stockType;
    private String referenceId;
    private String idempotencyKey;
}
