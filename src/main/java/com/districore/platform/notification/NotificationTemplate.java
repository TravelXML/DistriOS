package com.districore.platform.notification;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificationTemplate {
    private String id;
    private String name;
    private String type;
    private String content;
}