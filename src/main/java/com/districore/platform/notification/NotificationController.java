package com.districore.platform.notification;

import com.districore.platform.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {
    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @PostMapping("/templates")
    public ResponseEntity<ApiResponse<NotificationTemplate>> createTemplate(@RequestBody NotificationTemplate template) {
        return ResponseEntity.ok(ApiResponse.<NotificationTemplate>builder()
                .success(true)
                .message("Notification template created")
                .data(service.createTemplate(template))
                .build());
    }

    @GetMapping("/templates")
    public ResponseEntity<ApiResponse<List<NotificationTemplate>>> listTemplates() {
        return ResponseEntity.ok(ApiResponse.<List<NotificationTemplate>>builder()
                .success(true)
                .message("Notification templates retrieved")
                .data(service.listTemplates())
                .build());
    }

    @PostMapping("/send-email")
    public ResponseEntity<ApiResponse<String>> sendEmail(@RequestParam String to, @RequestParam String templateId) {
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .message("Email sent")
                .data(service.sendEmail(to, templateId))
                .build());
    }

    @PostMapping("/send-sms")
    public ResponseEntity<ApiResponse<String>> sendSms(@RequestParam String to, @RequestParam String templateId) {
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .message("SMS sent")
                .data(service.sendSms(to, templateId))
                .build());
    }

    @PostMapping("/send-whatsapp")
    public ResponseEntity<ApiResponse<String>> sendWhatsapp(@RequestParam String to, @RequestParam String templateId) {
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .message("WhatsApp message sent")
                .data(service.sendWhatsapp(to, templateId))
                .build());
    }

    @GetMapping("/in-app")
    public ResponseEntity<ApiResponse<List<String>>> getInAppNotifications(@RequestParam String userId) {
        return ResponseEntity.ok(ApiResponse.<List<String>>builder()
                .success(true)
                .message("In-app notifications retrieved")
                .data(service.getInAppNotifications(userId))
                .build());
    }
}