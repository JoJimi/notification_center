package com.example.dto.convert;

import com.example.domain.notification.NotificationType;
import lombok.Getter;

import java.time.Instant;

@Getter
public class ConvertedFollowNotification extends ConvertedNotification{
    private final String userName;
    private final String userProfileImageUrl;
    private final boolean following;

    public ConvertedFollowNotification(String id, NotificationType type, Instant occurredAt, Instant lastUpdatedAt,
                                       String userName, String userProfileImageUrl, boolean following) {

        super(id, type, occurredAt, lastUpdatedAt);
        this.userName = userName;
        this.userProfileImageUrl = userProfileImageUrl;
        this.following = following;
    }
}
