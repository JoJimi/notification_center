package com.example.notification.entity;

import com.example.notification.type.NotificationType;
import lombok.AllArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
public class Notification {
    public String id;
    public Long userId;
    public NotificationType notificationType;
    public Instant createdAt;
    public Instant deletedAt;
}
