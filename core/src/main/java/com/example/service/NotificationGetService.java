package com.example.service;


import com.example.domain.notification.Notification;
import com.example.domain.notification.NotificationType;
import com.example.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class NotificationGetService {
    @Autowired
    private NotificationRepository repository;

    public Optional<Notification> getNotification(NotificationType type, Long commentId){
        return repository.findByTypeAndCommentId(type, commentId);
    }
}
