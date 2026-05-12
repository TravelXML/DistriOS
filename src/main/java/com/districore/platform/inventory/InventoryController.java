package com.districore.platform.inventory;

import com.districore.platform.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {
    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<WarehouseResponse>>> listWarehouses() {
        return ResponseEntity.ok(ApiResponse.<List<WarehouseResponse>>builder()
                .success(true)
                .message("Warehouses retrieved")
                .data(service.listWarehouses())
                .build());
    }

    @GetMapping("/warehouses/{warehouseId}")
    public ResponseEntity<ApiResponse<WarehouseResponse>> warehouse(@PathVariable String warehouseId) {
        return ResponseEntity.ok(ApiResponse.<WarehouseResponse>builder()
                .success(true)
                .message("Warehouse details retrieved")
                .data(service.getWarehouse(warehouseId))
                .build());
    }

    @PostMapping("/stock-in")
    public ResponseEntity<ApiResponse<InventoryTransactionResponse>> stockIn(@Valid @RequestBody StockMovementRequest request) {
        return ResponseEntity.ok(ApiResponse.<InventoryTransactionResponse>builder()
                .success(true)
                .message("Stock in recorded")
                .data(service.stockIn(request))
                .build());
    }

    @PostMapping("/stock-out")
    public ResponseEntity<ApiResponse<InventoryTransactionResponse>> stockOut(@Valid @RequestBody StockMovementRequest request) {
        return ResponseEntity.ok(ApiResponse.<InventoryTransactionResponse>builder()
                .success(true)
                .message("Stock out recorded")
                .data(service.stockOut(request))
                .build());
    }

    @PostMapping("/adjustments")
    public ResponseEntity<ApiResponse<InventoryTransactionResponse>> adjust(@Valid @RequestBody StockMovementRequest request) {
        return ResponseEntity.ok(ApiResponse.<InventoryTransactionResponse>builder()
                .success(true)
                .message("Stock adjustment recorded")
                .data(service.adjustStock(request))
                .build());
    }

    @GetMapping("/transactions")
    public ResponseEntity<ApiResponse<List<InventoryTransactionResponse>>> transactions() {
        return ResponseEntity.ok(ApiResponse.<List<InventoryTransactionResponse>>builder()
                .success(true)
                .message("Inventory transactions retrieved")
                .data(service.listTransactions())
                .build());
    }

    @GetMapping("/low-stock")
    public ResponseEntity<ApiResponse<List<InventoryTransactionResponse>>> lowStock() {
        return ResponseEntity.ok(ApiResponse.<List<InventoryTransactionResponse>>builder()
                .success(true)
                .message("Low stock list retrieved")
                .data(service.lowStock())
                .build());
    }

    @GetMapping("/near-expiry")
    public ResponseEntity<ApiResponse<List<InventoryTransactionResponse>>> nearExpiry() {
        return ResponseEntity.ok(ApiResponse.<List<InventoryTransactionResponse>>builder()
                .success(true)
                .message("Near expiry stock retrieved")
                .data(service.nearExpiry())
                .build());
    }

    @GetMapping("/expired-stock")
    public ResponseEntity<ApiResponse<List<InventoryTransactionResponse>>> expiredStock() {
        return ResponseEntity.ok(ApiResponse.<List<InventoryTransactionResponse>>builder()
                .success(true)
                .message("Expired stock retrieved")
                .data(service.expiredStock())
                .build());
    }
}
