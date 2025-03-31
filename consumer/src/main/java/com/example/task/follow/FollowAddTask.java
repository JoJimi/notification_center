package com.example.task.follow;

import com.example.domain.follow.FollowNotification;
import com.example.domain.notification.NotificationType;
import com.example.event.follow.FollowEvent;
import com.example.service.NotificationSaveService;
import com.example.utils.NotificationIdGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Slf4j
@Component
@AllArgsConstructor
public class FollowAddTask {

    private final NotificationSaveService saveService;

    public void processEvent(FollowEvent event){
        saveService.insert(createFollowNotification(event));
    }

    private static FollowNotification createFollowNotification(FollowEvent event) {
        Instant now = Instant.now();

        return new FollowNotification(
                NotificationIdGenerator.generate(),
                event.getTargetUserId(),
                NotificationType.FOLLOW,
                event.getCreatedAt(),
                now,
                now,
                now.plus(90, ChronoUnit.DAYS),
                event.getUserId()
        );
    }
}
