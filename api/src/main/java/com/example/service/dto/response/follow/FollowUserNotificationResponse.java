package com.example.service.dto.response.follow;

import com.example.domain.notification.NotificationType;
import com.example.service.dto.convert.ConvertedFollowNotification;
import com.example.service.dto.response.user.UserNotificationResponse;
import lombok.Getter;

import java.time.Instant;

@Getter
public class FollowUserNotificationResponse extends UserNotificationResponse {

    private final String userName;
    private final String userProfileImageUrl;
    private final boolean isFollowing;

    public FollowUserNotificationResponse(String id, NotificationType type, Instant occurredAt, String userName, String userProfileImageUrl, boolean isFollowing) {
        super(id, type, occurredAt);
        this.userName = userName;
        this.userProfileImageUrl = userProfileImageUrl;
        this.isFollowing = isFollowing;
    }

    public static FollowUserNotificationResponse of(ConvertedFollowNotification notification){
        return new FollowUserNotificationResponse(
                notification.getId(),
                notification.getType(),
                notification.getOccurredAt(),
                notification.getUserName(),
                notification.getUserProfileImageUrl(),
                notification.isFollowing()
        );
    }

}
