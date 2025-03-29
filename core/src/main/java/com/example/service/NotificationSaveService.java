package com.example.service;


import com.example.domain.Notification;
import com.example.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationSaveService {
    @Autowired
    private NotificationRepository repository;

    public void insert(Notification notification){
        Notification result = repository.insert(notification);
        log.info("inserted: {}", result);
    }
}
