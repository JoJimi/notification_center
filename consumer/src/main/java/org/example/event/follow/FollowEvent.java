package org.example.event.follow;

import lombok.Data;

import java.time.Instant;

@Data
public class FollowEvent {
    private FollowEventType type;
    private long userId;
    private long targetUserId;
    private Instant createdAt;
}
