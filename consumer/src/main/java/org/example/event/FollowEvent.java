package org.example.event;

import lombok.Data;

import java.time.Instant;

@Data
public class FollowEvent {
    private FollowEventType type;
    private long userId;
    private long targetUserId;
    private Instant createdAt;
}
