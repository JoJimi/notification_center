package com.example.task.like;

import com.example.domain.like.LikeNotification;
import com.example.domain.notification.Notification;
import com.example.event.like.LikeEvent;
import com.example.service.NotificationGetService;
import com.example.service.NotificationRemoveService;
import com.example.service.NotificationSaveService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

import static com.example.domain.notification.NotificationType.*;

@Slf4j
@Component
@AllArgsConstructor
public class LikeRemoveTask {

    private final NotificationGetService getService;
    private final NotificationRemoveService removeService;
    private final NotificationSaveService saveService;

    public void processEvent(LikeEvent event){
        Optional<Notification> optionalNotification = getService.getNotificationByTypeAndPostId(LIKE, event.getPostId());
        if(optionalNotification.isEmpty()){
            log.error("No notification with postID: {}", event.getPostId());
            return;
        }

        LikeNotification notification = (LikeNotification) optionalNotification.get();
        removeLikerAndUpdateNotification(notification, event);
    }

    private void removeLikerAndUpdateNotification(LikeNotification notification, LikeEvent event){
        notification.removeLiker(event.getUserId(), Instant.now());

        if(notification.getLikerIds().isEmpty()){
            removeService.deleteById(notification.getId());
        }
        else {
            saveService.upsert(notification);
        }
    }
}
