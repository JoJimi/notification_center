package com.example.service.dto.response.user;

import com.example.service.dto.GetUserNotificationsResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
@AllArgsConstructor
public class UserNotificationListResponse {

    private List<UserNotificationResponse> notifications;
    private boolean hasNext;
    private Instant pivot;

    public static UserNotificationListResponse of(GetUserNotificationsResult result){
        List<UserNotificationResponse> notifications = result.getNotifications().stream()
                .map(UserNotificationResponse::of)
                .toList();

        Instant pivot = (result.isHasNext() && !notifications.isEmpty())
                ? notifications.getLast().getOccurredAt() : null;

        return new UserNotificationListResponse(
                notifications,
                result.isHasNext(),
                pivot
        );
    }

}
