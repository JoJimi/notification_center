package com.example.dto.response.follow;

import com.example.domain.notification.NotificationType;
import com.example.dto.convert.ConvertedFollowNotification;
import com.example.dto.response.user.UserNotificationResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.Instant;

@Getter
@Schema(description = "팔로우 알림 응답")
public class FollowUserNotificationResponse extends UserNotificationResponse {

    @Schema(description = "팔로우한 사용자 이름")
    private final String userName;

    @Schema(description = "팔로우한 사용자 프로필 이미지")
    private final String userProfileImageUrl;

    @Schema(description = "팔로우 여부")
    private final boolean following;

    public FollowUserNotificationResponse(String id, NotificationType type, Instant occurredAt, String userName,
                                          String userProfileImageUrl, boolean following) {
        super(id, type, occurredAt);
        this.userName = userName;
        this.userProfileImageUrl = userProfileImageUrl;
        this.following = following;
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
