package com.districore.platform.notification;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class NotificationService {

    public NotificationTemplate createTemplate(NotificationTemplate template) {
        // Mock implementation
        return new NotificationTemplate(UUID.randomUUID().toString(), template.getName(), template.getType(), template.getContent());
    }

    public List<NotificationTemplate> listTemplates() {
        // Mock implementation
        return Arrays.asList(
                new NotificationTemplate("1", "Order Confirmation", "EMAIL", "Your order has been confirmed"),
                new NotificationTemplate("2", "Payment Reminder", "SMS", "Payment due for invoice #{{invoiceId}}")
        );
    }

    public String sendEmail(String to, String templateId) {
        // Mock implementation
        return "Email sent to " + to + " using template " + templateId;
    }

    public String sendSms(String to, String templateId) {
        // Mock implementation
        return "SMS sent to " + to + " using template " + templateId;
    }

    public String sendWhatsapp(String to, String templateId) {
        // Mock implementation
        return "WhatsApp message sent to " + to + " using template " + templateId;
    }

    public List<String> getInAppNotifications(String userId) {
        // Mock implementation
        return Arrays.asList(
                "New order received",
                "Payment reminder for invoice #123",
                "Scheme ending soon"
        );
    }
}