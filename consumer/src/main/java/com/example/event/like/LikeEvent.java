package com.example.event.like;

import lombok.Data;

import java.time.Instant;

@Data
public class LikeEvent {
    private LikeEventType type;
    private long postId;
    private long userId;
    private Instant createdAt;
}
