package com.districore.platform.inventory;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class InventoryTransactionResponse {
    private String id;
    private String warehouseId;
    private String productId;
    private InventoryTransactionType transactionType;
    private InventoryStockType stockType;
    private BigDecimal quantity;
    private String referenceId;
}
