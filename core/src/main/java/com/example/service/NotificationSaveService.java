package com.example.service;


import com.example.domain.notification.Notification;
import com.example.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class NotificationSaveService {

    private final NotificationRepository repository;

    public void insert(Notification notification){
        Notification result = repository.insert(notification);
        log.info("inserted: {}", result);
    }

    // Update + Insert
    public void upsert(Notification notification){
        Notification result = repository.save(notification);
        log.info("upserted: {}", result);
    }
}
