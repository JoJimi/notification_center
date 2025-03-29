package com.example;

import lombok.Getter;
import org.springframework.data.annotation.TypeAlias;

import java.time.Instant;

@Getter
@TypeAlias("CommentNotification")
public class CommentNotification extends Notification{
    private final Long postId;
    private final Long writerId;
    private final String comment;

    public CommentNotification(String id, Long userId, NotificationType type, Instant occurredAt, Instant createdAt, Instant lastUpdateAt, Instant deletedAt, Long postId, Long writerId, String comment) {
        super(id, userId, type, occurredAt, createdAt, lastUpdateAt, deletedAt);
        this.postId = postId;
        this.writerId = writerId;
        this.comment = comment;
    }
}
