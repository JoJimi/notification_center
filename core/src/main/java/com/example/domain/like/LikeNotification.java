package com.example.domain.like;

import com.example.domain.notification.Notification;
import com.example.domain.notification.NotificationType;
import lombok.Getter;
import org.springframework.data.annotation.TypeAlias;

import java.time.Instant;
import java.util.List;

@Getter
@TypeAlias("LikeNotification")
public class LikeNotification extends Notification {
    private final Long postId;
    private final List<Long> likerIds;


    public LikeNotification(String id, Long userId, NotificationType type, Instant occurredAt, Instant createdAt, Instant lastUpdateAt, Instant deletedAt, Long postId, List<Long> likerId) {
        super(id, userId, type, occurredAt, createdAt, lastUpdateAt, deletedAt);
        this.postId = postId;
        this.likerIds = likerId;
    }
}
