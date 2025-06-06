package com.example.service;


import com.example.domain.notification.Notification;
import com.example.domain.notification.NotificationType;
import com.example.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class NotificationGetService {

    private final NotificationRepository repository;

    public Optional<Notification> getNotificationByTypeAndCommentId(NotificationType type, Long commentId){
        return repository.findByTypeAndCommentId(type, commentId);
    }

    public Optional<Notification> getNotificationByTypeAndPostId(NotificationType type, Long postId){
        return repository.findByTypeAndPostId(type, postId);
    }

    public Optional<Notification> getNotificationByTypeAndUserIdAndFollowerId(NotificationType type, Long userId, Long followerId){
        return repository.findByTypeAndUserIdAndFollowerId(type, userId, followerId);
    }

    public Instant getLatestUpdatedAt(Long userId){
        Optional<Notification> notification = repository.findFirstByUserIdOrderByLastUpdateAtDesc(userId);

        return notification.map(Notification::getLastUpdateAt).orElse(null);

    }
}
