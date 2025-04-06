package com.example.controller;

import com.example.service.GetUserNotificationsService;
import com.example.dto.response.user.UserNotificationListResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/v1/user-notification")
@AllArgsConstructor
public class UserNotificationListController implements UserNotificationListControllerSpec {

    private final GetUserNotificationsService getUserNotificationService;

    @Override
    @GetMapping("/{userId}")
    public UserNotificationListResponse getNotifications(
            @PathVariable(value = "userId") Long userId,
            @RequestParam(value = "pivot", required = false) Instant pivot
    ){
        return UserNotificationListResponse.of(
                getUserNotificationService.getUserNotificationsByPivot(userId, pivot));
    }
}
