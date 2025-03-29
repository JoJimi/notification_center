package com.example;


import com.example.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class NotificationRemoveService {
    @Autowired
    private NotificationRepository repository;

    public void deleteById(String id){
        log.info("deleted: {}", id);
        repository.deleteById(id);
    }
}
