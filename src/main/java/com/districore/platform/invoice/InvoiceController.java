package com.districore.platform.invoice;

import com.districore.platform.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceController {
    private final InvoiceService service;

    public InvoiceController(InvoiceService service) {
        this.service = service;
    }

    @PostMapping("/from-order/{orderId}")
    public ResponseEntity<ApiResponse<InvoiceResponse>> createFromOrder(@PathVariable UUID orderId) {
        return ResponseEntity.ok(ApiResponse.<InvoiceResponse>builder()
                .success(true)
                .message("Invoice generated")
                .data(service.generateFromOrder(orderId))
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<InvoiceResponse>>> list() {
        return ResponseEntity.ok(ApiResponse.<List<InvoiceResponse>>builder()
                .success(true)
                .message("Invoices retrieved")
                .data(service.listInvoices())
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<InvoiceResponse>> get(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.<InvoiceResponse>builder()
                .success(true)
                .message("Invoice retrieved")
                .data(service.getInvoice(id))
                .build());
    }

    @GetMapping("/{id}/tax-summary")
    public ResponseEntity<ApiResponse<InvoiceTaxSummaryResponse>> taxSummary(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.<InvoiceTaxSummaryResponse>builder()
                .success(true)
                .message("Invoice tax summary")
                .data(service.getTaxSummary(id))
                .build());
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<ApiResponse<InvoiceResponse>> cancel(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.<InvoiceResponse>builder()
                .success(true)
                .message("Invoice cancelled")
                .data(service.cancelInvoice(id))
                .build());
    }
}
