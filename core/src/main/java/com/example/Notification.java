package com.example;

import lombok.AllArgsConstructor;
import org.springframework.data.annotation.PersistenceConstructor;

import java.time.Instant;

enum NotificationType {
    LIKE,
    COMMENT,
    FOLLOW,
}

@AllArgsConstructor
public class Notification {
    public String id;
    public Long userId;
    public NotificationType notificationType;
    public Instant createdAt;
    public Instant deletedAt;
}
