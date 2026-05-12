package com.districore.platform.invoice;

import com.districore.platform.common.ResourceNotFoundException;
import com.districore.platform.common.TenantContext;
import com.districore.platform.order.Order;
import com.districore.platform.order.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final OrderRepository orderRepository;

    public InvoiceService(InvoiceRepository invoiceRepository, OrderRepository orderRepository) {
        this.invoiceRepository = invoiceRepository;
        this.orderRepository = orderRepository;
    }

    public InvoiceResponse generateFromOrder(UUID orderId) {
        Order order = orderRepository.findByIdAndTenantId(orderId, TenantContext.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        BigDecimal total = order.getLineItems().stream().map(item -> item.getTotalPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
        Invoice invoice = new Invoice();
        invoice.setOrderId(orderId.toString());
        invoice.setRetailerId(order.getRetailerId());
        invoice.setTotalAmount(total);
        invoice.setGeneratedAt(Instant.now());
        invoice.setStatus(InvoiceStatus.GENERATED);
        invoice.setTenantId(TenantContext.getTenantId());
        invoiceRepository.save(invoice);
        return toResponse(invoice);
    }

    public List<InvoiceResponse> listInvoices() {
        return invoiceRepository.findByTenantId(TenantContext.getTenantId()).stream().map(this::toResponse).collect(Collectors.toList());
    }

    public InvoiceResponse getInvoice(UUID id) {
        return toResponse(invoiceRepository.findByIdAndTenantId(id, TenantContext.getTenantId()).orElseThrow(() -> new ResourceNotFoundException("Invoice not found")));
    }

    public InvoiceTaxSummaryResponse getTaxSummary(UUID id) {
        Invoice invoice = invoiceRepository.findByIdAndTenantId(id, TenantContext.getTenantId()).orElseThrow(() -> new ResourceNotFoundException("Invoice not found"));
        return new InvoiceTaxSummaryResponse(invoice.getId().toString(), invoice.getTotalAmount(), invoice.getTotalAmount().multiply(BigDecimal.valueOf(0.18))); 
    }

    public InvoiceResponse cancelInvoice(UUID id) {
        Invoice invoice = invoiceRepository.findByIdAndTenantId(id, TenantContext.getTenantId()).orElseThrow(() -> new ResourceNotFoundException("Invoice not found"));
        invoice.setStatus(InvoiceStatus.CANCELLED);
        return toResponse(invoice);
    }

    private InvoiceResponse toResponse(Invoice invoice) {
        return new InvoiceResponse(invoice.getId().toString(), invoice.getOrderId(), invoice.getRetailerId(), invoice.getTotalAmount(), invoice.getStatus(), invoice.getGeneratedAt());
    }
}
