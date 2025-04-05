package com.example.service.dto.response.user;

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

}
