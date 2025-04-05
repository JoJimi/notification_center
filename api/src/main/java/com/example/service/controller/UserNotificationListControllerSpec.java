package com.example.service.controller;


import com.example.service.dto.response.user.UserNotificationListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;

import java.time.Instant;

@Tag(name = "사용자 알림센터 API")
public interface UserNotificationListControllerSpec {

    @Operation(summary = "사용자 알림 목록 조회")
    UserNotificationListResponse getNotifications(
            @Parameter(example = "1") Long userId,
            @Parameter(example = "2024-01-01T00:11:22.382Z") Instant pivot
    );

}
