package com.districore.platform.order;

import com.districore.platform.common.ResourceNotFoundException;
import com.districore.platform.common.TenantContext;
import com.districore.platform.distributor.DistributorRepository;
import com.districore.platform.order.OrderStatusHistoryRepository;
import com.districore.platform.product.ProductRepository;
import com.districore.platform.product.Product;
import com.districore.platform.retailer.RetailerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.districore.platform.order.OrderLineItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final RetailerRepository retailerRepository;
    private final DistributorRepository distributorRepository;
    private final OrderStatusHistoryRepository historyRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository,
                        RetailerRepository retailerRepository, DistributorRepository distributorRepository,
                        OrderStatusHistoryRepository historyRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.retailerRepository = retailerRepository;
        this.distributorRepository = distributorRepository;
        this.historyRepository = historyRepository;
    }

    public OrderResponse createOrder(OrderRequest request) {
        if (request.getIdempotencyKey() != null) {
            orderRepository.findByIdempotencyKeyAndTenantId(request.getIdempotencyKey(), TenantContext.getTenantId()).ifPresent(order -> {
                throw new ResourceNotFoundException("Order already exists for this idempotency key");
            });
        }
        retailerRepository.findByIdAndTenantId(UUID.fromString(request.getRetailerId()), TenantContext.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Retailer not found"));
        distributorRepository.findByIdAndTenantId(UUID.fromString(request.getDistributorId()), TenantContext.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Distributor not found"));
        Order order = new Order();
        order.setRetailerId(request.getRetailerId());
        order.setDistributorId(request.getDistributorId());
        order.setStatus(OrderStatus.CREATED);
        order.setSource(request.getSource());
        order.setIdempotencyKey(request.getIdempotencyKey());
        order.setTenantId(TenantContext.getTenantId());
        for (OrderLineReq itemReq : request.getItems()) {
            Product product = productRepository.findByIdAndTenantId(UUID.fromString(itemReq.getProductId()), TenantContext.getTenantId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
            OrderLineItem item = new OrderLineItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(itemReq.getQuantity());
            item.setUnitPrice(itemReq.getUnitPrice());
            item.setTotalPrice(itemReq.getUnitPrice().multiply(BigDecimal.valueOf(itemReq.getQuantity())));
            order.getLineItems().add(item);
        }
        orderRepository.save(order);
        recordStatusHistory(order, OrderStatus.CREATED);
        return toResponse(order);
    }

    public List<OrderResponse> listOrders() {
        return orderRepository.findByTenantId(TenantContext.getTenantId()).stream().map(this::toResponse).collect(Collectors.toList());
    }

    public OrderResponse getOrder(UUID id) {
        return toResponse(orderRepository.findByIdAndTenantId(id, TenantContext.getTenantId()).orElseThrow(() -> new ResourceNotFoundException("Order not found")));
    }

    public OrderResponse confirmOrder(UUID id) {
        Order order = findOrder(id);
        order.setStatus(OrderStatus.CONFIRMED);
        orderRepository.save(order);
        recordStatusHistory(order, OrderStatus.CONFIRMED);
        return toResponse(order);
    }

    public OrderResponse cancelOrder(UUID id) {
        Order order = findOrder(id);
        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
        recordStatusHistory(order, OrderStatus.CANCELLED);
        return toResponse(order);
    }

    public OrderResponse dispatchOrder(UUID id) {
        Order order = findOrder(id);
        order.setStatus(OrderStatus.DISPATCHED);
        orderRepository.save(order);
        recordStatusHistory(order, OrderStatus.DISPATCHED);
        return toResponse(order);
    }

    public OrderResponse deliverOrder(UUID id) {
        Order order = findOrder(id);
        order.setStatus(OrderStatus.DELIVERED);
        orderRepository.save(order);
        recordStatusHistory(order, OrderStatus.DELIVERED);
        return toResponse(order);
    }

    public java.util.List<OrderStatusHistoryResponse> getStatusHistory(UUID orderId) {
        return historyRepository.findByOrderIdAndTenantId(orderId, TenantContext.getTenantId()).stream()
                .map(history -> new OrderStatusHistoryResponse(history.getStatus().name(), history.getStatusUpdatedAt()))
                .collect(Collectors.toList());
    }

    private Order findOrder(UUID id) {
        return orderRepository.findByIdAndTenantId(id, TenantContext.getTenantId()).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    private void recordStatusHistory(Order order, OrderStatus status) {
        OrderStatusHistory history = new OrderStatusHistory();
        history.setOrder(order);
        history.setStatus(status);
        history.setTenantId(TenantContext.getTenantId());
        historyRepository.save(history);
    }

    private OrderResponse toResponse(Order order) {
        return new OrderResponse(order.getId().toString(), order.getRetailerId(), order.getDistributorId(), order.getStatus(), order.getSource(), order.getLineItems().stream().map(item -> new OrderLineResponse(item.getProduct().getId().toString(), item.getQuantity(), item.getUnitPrice(), item.getTotalPrice())).collect(Collectors.toList()));
    }
}
