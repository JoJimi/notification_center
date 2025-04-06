package com.example.service;

import com.example.domain.comment.CommentNotification;
import com.example.domain.follow.FollowNotification;
import com.example.domain.like.LikeNotification;
import com.example.dto.GetUserNotificationsByPivotResult;
import com.example.convertor.CommentUserNotificationConvertor;
import com.example.convertor.FollowUserNotificationConvertor;
import com.example.convertor.LikeUserNotificationConvertor;
import com.example.dto.convert.ConvertedNotification;
import com.example.dto.GetUserNotificationsResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@AllArgsConstructor
public class GetUserNotificationsService {

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
