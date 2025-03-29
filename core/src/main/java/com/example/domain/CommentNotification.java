package com.example.domain;

import lombok.Getter;
import org.springframework.data.annotation.TypeAlias;

import java.time.Instant;

@Getter
@TypeAlias("CommentNotification")
public class CommentNotification extends Notification{
    private final Long postId;
    private final Long writerId;
    private final String comment;
    private final Long commentId;

    public CommentNotification(String id, long userId, NotificationType type, Instant occurredAt, Instant createdAt, Instant lastUpdateAt, Instant deletedAt, long postId, long writerId, String comment, Long commentId) {
        super(id, userId, type, occurredAt, createdAt, lastUpdateAt, deletedAt);
        this.postId = postId;
        this.writerId = writerId;
        this.comment = comment;
        this.commentId = commentId;
    }
}
