package com.example.service;

import com.example.domain.comment.CommentNotification;
import com.example.domain.follow.FollowNotification;
import com.example.domain.like.LikeNotification;
import com.example.dto.GetUserNotificationsByPivotResult;
import com.example.service.convertor.CommentUserNotificationConvertor;
import com.example.service.convertor.FollowUserNotificationConvertor;
import com.example.service.convertor.LikeUserNotificationConvertor;
import com.example.service.dto.ConvertedNotification;
import com.example.service.dto.GetUserNotificationsResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class GetUserNotificationService {

    private final NotificationListService listService;
    private final CommentUserNotificationConvertor commentConvertor;
    private final LikeUserNotificationConvertor likeConvertor;
    private final FollowUserNotificationConvertor followConvertor;

    public GetUserNotificationsResult getUserNotificationsByPivot(Long userId, Instant pivot){

        GetUserNotificationsByPivotResult result = listService.getUserNotificationsByPivot(userId, pivot);

        List<ConvertedNotification> convertedNotifications = result.getNotifications().stream()
                .map(notification -> switch (notification.getType()){
                            case COMMENT -> commentConvertor.convert((CommentNotification) notification);
                            case LIKE -> likeConvertor.convert((LikeNotification) notification);
                            case FOLLOW -> followConvertor.convert((FollowNotification) notification);
                })
                .toList();

        return new GetUserNotificationsResult(
                convertedNotifications,
                result.isHasNext()
        );
    }
}
