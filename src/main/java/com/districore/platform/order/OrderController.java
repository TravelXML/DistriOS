package com.districore.platform.order;

import com.districore.platform.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> create(@Valid @RequestBody OrderRequest request) {
        return ResponseEntity.ok(ApiResponse.<OrderResponse>builder()
                .success(true)
                .message("Order created")
                .data(service.createOrder(request))
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderResponse>>> list() {
        return ResponseEntity.ok(ApiResponse.<List<OrderResponse>>builder()
                .success(true)
                .message("Orders retrieved")
                .data(service.listOrders())
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponse>> get(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.<OrderResponse>builder()
                .success(true)
                .message("Order retrieved")
                .data(service.getOrder(id))
                .build());
    }

    @PatchMapping("/{id}/confirm")
    public ResponseEntity<ApiResponse<OrderResponse>> confirm(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.<OrderResponse>builder()
                .success(true)
                .message("Order confirmed")
                .data(service.confirmOrder(id))
                .build());
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<ApiResponse<OrderResponse>> cancel(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.<OrderResponse>builder()
                .success(true)
                .message("Order cancelled")
                .data(service.cancelOrder(id))
                .build());
    }

    @PatchMapping("/{id}/dispatch")
    public ResponseEntity<ApiResponse<OrderResponse>> dispatch(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.<OrderResponse>builder()
                .success(true)
                .message("Order dispatched")
                .data(service.dispatchOrder(id))
                .build());
    }

    @PatchMapping("/{id}/deliver")
    public ResponseEntity<ApiResponse<OrderResponse>> deliver(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.<OrderResponse>builder()
                .success(true)
                .message("Order delivered")
                .data(service.deliverOrder(id))
                .build());
    }

    @GetMapping("/{id}/status-history")
    public ResponseEntity<ApiResponse<java.util.List<OrderStatusHistoryResponse>>> statusHistory(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.<java.util.List<OrderStatusHistoryResponse>>builder()
                .success(true)
                .message("Order status history retrieved")
                .data(service.getStatusHistory(id))
                .build());
    }
}
