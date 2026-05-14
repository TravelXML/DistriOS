package com.districore.platform.integration;

import com.districore.platform.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/integrations")
public class IntegrationController {
    private final IntegrationService service;

    public IntegrationController(IntegrationService service) {
        this.service = service;
    }

    @PostMapping("/webhooks")
    public ResponseEntity<ApiResponse<String>> registerWebhook(@Valid @RequestBody WebhookRequest request) {
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .message("Webhook registered")
                .data(service.registerWebhook(request))
                .build());
    }

    @GetMapping("/webhooks")
    public ResponseEntity<ApiResponse<String>> listWebhooks() {
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .message("Webhooks retrieved")
                .data(service.listWebhooks())
                .build());
    }

    @PostMapping("/erp/push-order")
    public ResponseEntity<ApiResponse<String>> pushOrderToErp(@RequestParam String orderId) {
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .message("Order pushed to ERP")
                .data(service.pushOrderToErp(orderId))
                .build());
    }

    @PostMapping("/erp/push-invoice")
    public ResponseEntity<ApiResponse<String>> pushInvoiceToErp(@RequestParam String invoiceId) {
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .message("Invoice pushed to ERP")
                .data(service.pushInvoiceToErp(invoiceId))
                .build());
    }

    @PostMapping("/tax/validate-gstin")
    public ResponseEntity<ApiResponse<String>> validateGstin(@RequestParam String gstin) {
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .message("GSTIN validated")
                .data(service.validateGstin(gstin))
                .build());
    }

    @PostMapping("/payment/callback")
    public ResponseEntity<ApiResponse<String>> paymentCallback(@RequestBody Object callback) {
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .message("Payment callback processed")
                .data(service.processPaymentCallback(callback))
                .build());
    }
}