package com.example.event.like;

import lombok.Data;

import java.time.Instant;

@Data
public class LikeEvent {
    private LikeEventType type;
    private Long postId;
    private Long userId;
    private Instant createdAt;
}
