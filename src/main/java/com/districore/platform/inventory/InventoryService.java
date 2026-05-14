package com.districore.platform.inventory;

import com.districore.platform.common.ResourceNotFoundException;
import com.districore.platform.common.TenantContext;
import com.districore.platform.product.ProductRepository;
import com.districore.platform.product.Product;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class InventoryService {
    private final WarehouseRepository warehouseRepository;
    private final InventoryTransactionRepository transactionRepository;
    private final ProductRepository productRepository;

    public InventoryService(WarehouseRepository warehouseRepository, InventoryTransactionRepository transactionRepository,
                            ProductRepository productRepository) {
        this.warehouseRepository = warehouseRepository;
        this.transactionRepository = transactionRepository;
        this.productRepository = productRepository;
    }

    public WarehouseResponse createWarehouse(WarehouseRequest request) {
        Warehouse warehouse = new Warehouse();
        warehouse.setName(request.getName());
        warehouse.setLocation(request.getLocation());
        warehouse.setManagerName(request.getManagerName());
        warehouse.setTenantId(TenantContext.getTenantId());
        warehouseRepository.save(warehouse);
        return new WarehouseResponse(warehouse.getId().toString(), warehouse.getName(), warehouse.getLocation(), warehouse.getManagerName());
    }

    public List<WarehouseResponse> listWarehouses() {
        return warehouseRepository.findByTenantId(TenantContext.getTenantId()).stream()
                .map(w -> new WarehouseResponse(w.getId().toString(), w.getName(), w.getLocation(), w.getManagerName()))
                .collect(Collectors.toList());
    }

    public InventoryTransactionResponse stockIn(StockMovementRequest request) {
        Warehouse warehouse = warehouseRepository.findByIdAndTenantId(UUID.fromString(request.getWarehouseId()), TenantContext.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found"));
        Product product = productRepository.findByIdAndTenantId(UUID.fromString(request.getProductId()), TenantContext.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        InventoryTransaction transaction = new InventoryTransaction();
        transaction.setWarehouse(warehouse);
        transaction.setProduct(product);
        transaction.setTransactionType(InventoryTransactionType.STOCK_IN);
        transaction.setStockType(request.getStockType());
        transaction.setQuantity(request.getQuantity());
        transaction.setReferenceId(request.getReferenceId());
        transaction.setTenantId(TenantContext.getTenantId());
        transactionRepository.save(transaction);
        return toResponse(transaction);
    }

    public InventoryTransactionResponse stockOut(StockMovementRequest request) {
        Warehouse warehouse = warehouseRepository.findByIdAndTenantId(UUID.fromString(request.getWarehouseId()), TenantContext.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found"));
        Product product = productRepository.findByIdAndTenantId(UUID.fromString(request.getProductId()), TenantContext.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        InventoryTransaction transaction = new InventoryTransaction();
        transaction.setWarehouse(warehouse);
        transaction.setProduct(product);
        transaction.setTransactionType(InventoryTransactionType.STOCK_OUT);
        transaction.setStockType(request.getStockType());
        transaction.setQuantity(request.getQuantity());
        transaction.setReferenceId(request.getReferenceId());
        transaction.setTenantId(TenantContext.getTenantId());
        transactionRepository.save(transaction);
        return toResponse(transaction);
    }

    public InventoryTransactionResponse adjustStock(StockMovementRequest request) {
        if (request.getIdempotencyKey() != null) {
            return transactionRepository.findByIdempotencyKeyAndTenantId(request.getIdempotencyKey(), TenantContext.getTenantId())
                    .map(this::toResponse)
                    .orElseGet(() -> createAdjustmentTransaction(request));
        }
        return createAdjustmentTransaction(request);
    }

    private InventoryTransactionResponse createAdjustmentTransaction(StockMovementRequest request) {
        Warehouse warehouse = warehouseRepository.findByIdAndTenantId(UUID.fromString(request.getWarehouseId()), TenantContext.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found"));
        Product product = productRepository.findByIdAndTenantId(UUID.fromString(request.getProductId()), TenantContext.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        InventoryTransaction transaction = new InventoryTransaction();
        transaction.setWarehouse(warehouse);
        transaction.setProduct(product);
        transaction.setTransactionType(InventoryTransactionType.ADJUSTMENT);
        transaction.setStockType(request.getStockType());
        transaction.setQuantity(request.getQuantity());
        transaction.setReferenceId(request.getReferenceId());
        transaction.setIdempotencyKey(request.getIdempotencyKey());
        transaction.setTenantId(TenantContext.getTenantId());
        transactionRepository.save(transaction);
        return toResponse(transaction);
    }

    public List<InventoryTransactionResponse> listTransactions() {
        return transactionRepository.findByTenantId(TenantContext.getTenantId()).stream().map(this::toResponse).collect(Collectors.toList());
    }

    public List<WarehouseResponse> listWarehouseDetails() {
        return listWarehouses();
    }

    public WarehouseResponse getWarehouse(String warehouseId) {
        Warehouse warehouse = warehouseRepository.findByIdAndTenantId(UUID.fromString(warehouseId), TenantContext.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found"));
        return new WarehouseResponse(warehouse.getId().toString(), warehouse.getName(), warehouse.getLocation(), warehouse.getManagerName());
    }

    public List<InventoryTransactionResponse> lowStock() {
        return listTransactions();
    }

    public List<InventoryTransactionResponse> nearExpiry() {
        return listTransactions();
    }

    public List<InventoryTransactionResponse> expiredStock() {
        return listTransactions();
    }

    public ProductAvailabilityResponse getProductAvailability(String productId) {
        List<InventoryTransaction> transactions = transactionRepository.findByProductIdAndTenantId(UUID.fromString(productId), TenantContext.getTenantId());
        java.math.BigDecimal totalIn = transactions.stream()
                .filter(tx -> tx.getTransactionType() == InventoryTransactionType.STOCK_IN || tx.getTransactionType() == InventoryTransactionType.ADJUSTMENT || tx.getTransactionType() == InventoryTransactionType.RETURN)
                .map(InventoryTransaction::getQuantity)
                .reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add);
        java.math.BigDecimal totalOut = transactions.stream()
                .filter(tx -> tx.getTransactionType() == InventoryTransactionType.STOCK_OUT || tx.getTransactionType() == InventoryTransactionType.DISPATCH || tx.getTransactionType() == InventoryTransactionType.DAMAGE || tx.getTransactionType() == InventoryTransactionType.EXPIRY)
                .map(InventoryTransaction::getQuantity)
                .reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add);
        java.math.BigDecimal available = totalIn.subtract(totalOut);
        return new ProductAvailabilityResponse(productId, totalIn, totalOut, available);
    }

    private InventoryTransactionResponse toResponse(InventoryTransaction transaction) {
        return new InventoryTransactionResponse(transaction.getId().toString(), transaction.getWarehouse().getId().toString(), transaction.getProduct().getId().toString(), transaction.getTransactionType(), transaction.getStockType(), transaction.getQuantity(), transaction.getReferenceId());
    }
}
