package com.districore.platform.order;

import com.districore.platform.common.ResourceNotFoundException;
import com.districore.platform.common.TenantContext;
import com.districore.platform.product.ProductRepository;
import com.districore.platform.product.Product;
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

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public OrderResponse createOrder(OrderRequest request) {
        if (request.getIdempotencyKey() != null) {
            orderRepository.findByIdempotencyKeyAndTenantId(request.getIdempotencyKey(), TenantContext.getTenantId()).ifPresent(order -> {
                throw new ResourceNotFoundException("Order already exists for this idempotency key");
            });
        }
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
        return toResponse(order);
    }

    public OrderResponse cancelOrder(UUID id) {
        Order order = findOrder(id);
        order.setStatus(OrderStatus.CANCELLED);
        return toResponse(order);
    }

    public OrderResponse dispatchOrder(UUID id) {
        Order order = findOrder(id);
        order.setStatus(OrderStatus.DISPATCHED);
        return toResponse(order);
    }

    public OrderResponse deliverOrder(UUID id) {
        Order order = findOrder(id);
        order.setStatus(OrderStatus.DELIVERED);
        return toResponse(order);
    }

    private Order findOrder(UUID id) {
        return orderRepository.findByIdAndTenantId(id, TenantContext.getTenantId()).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    private OrderResponse toResponse(Order order) {
        return new OrderResponse(order.getId().toString(), order.getRetailerId(), order.getDistributorId(), order.getStatus(), order.getSource(), order.getLineItems().stream().map(item -> new OrderLineResponse(item.getProduct().getId().toString(), item.getQuantity(), item.getUnitPrice(), item.getTotalPrice())).collect(Collectors.toList()));
    }
}
