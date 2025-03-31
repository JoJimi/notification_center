package com.example.domain.follow;

import com.example.domain.notification.Notification;
import com.example.domain.notification.NotificationType;
import lombok.Getter;
import org.springframework.data.annotation.TypeAlias;

import java.time.Instant;

@Getter
@TypeAlias("FollowNotification")
public class FollowNotification extends Notification {
    private final Long followerId;

    public FollowNotification(String id, Long userId, NotificationType type, Instant occurredAt, Instant createdAt, Instant lastUpdateAt, Instant deletedAt, Long followerId) {
        super(id, userId, type, occurredAt, createdAt, lastUpdateAt, deletedAt);
        this.followerId = followerId;
    }
}
