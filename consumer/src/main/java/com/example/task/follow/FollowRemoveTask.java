package com.example.task.follow;

import com.example.domain.notification.Notification;
import com.example.domain.notification.NotificationType;
import com.example.event.follow.FollowEvent;
import com.example.service.NotificationGetService;
import com.example.service.NotificationRemoveService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.example.domain.notification.NotificationType.*;

@Slf4j
@Component
@AllArgsConstructor
public class FollowRemoveTask {

    private final NotificationGetService getService;
    private final NotificationRemoveService removeService;

    public void processEvent(FollowEvent event){
        getService.getNotificationByTypeAndUserIdAndFollowerId(FOLLOW, event.getTargetUserId(), event.getUserId())
                        .ifPresent(
                                notification -> removeService.deleteById(notification.getId())
                        );
    }
}
