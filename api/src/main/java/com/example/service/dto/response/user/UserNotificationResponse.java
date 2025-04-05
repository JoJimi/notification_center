package com.example.service.dto.response.user;

import com.example.domain.notification.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class UserNotificationResponse {

    private String id;
    private NotificationType type;
    private Instant occurredAt;
}
