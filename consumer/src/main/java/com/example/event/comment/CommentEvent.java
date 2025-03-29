package com.example.event.comment;

import lombok.Data;

@Data
public class CommentEvent {
    private CommentEventType type;
    private long postId;
    private long userId;
    private long commentId;
}
