package com.example.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@AllArgsConstructor
public class CheckNewNotificationService {

    private final NotificationGetService notificationGetService;
    private final LastReadAtService lastReadAtService;
    public boolean checkNewNotification(long userId){
        Instant lastestUpdatedAt = notificationGetService.getLatestUpdatedAt(userId);

        if(lastestUpdatedAt == null){
            return false;
        }

        Instant lastReadAt = lastReadAtService.getLastReadAt(userId);

        if(lastReadAt == null){
            return true;
        }

        return lastestUpdatedAt.isAfter(lastReadAt);
    }

}
