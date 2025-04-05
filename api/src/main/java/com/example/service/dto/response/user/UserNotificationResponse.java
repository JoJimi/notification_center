package com.example.service.dto.response.user;

import com.example.domain.notification.NotificationType;
import com.example.service.dto.convert.ConvertedCommentNotification;
import com.example.service.dto.convert.ConvertedFollowNotification;
import com.example.service.dto.convert.ConvertedLikeNotification;
import com.example.service.dto.convert.ConvertedNotification;
import com.example.service.dto.response.comment.CommentUserNotificationResponse;
import com.example.service.dto.response.follow.FollowUserNotificationResponse;
import com.example.service.dto.response.like.LikeUserNotificationResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public abstract class UserNotificationResponse {

    private String id;
    private NotificationType type;
    private Instant occurredAt;

    public static UserNotificationResponse of(ConvertedNotification notification) {
        switch (notification.getType()){
            case COMMENT -> { return CommentUserNotificationResponse.of((ConvertedCommentNotification) notification); }
            case LIKE -> { return LikeUserNotificationResponse.of((ConvertedLikeNotification) notification); }
            case FOLLOW -> { return FollowUserNotificationResponse.of((ConvertedFollowNotification) notification); }
            default -> throw new IllegalArgumentException("Unsupported notification type: " + notification.getType());
        }
    }
}
