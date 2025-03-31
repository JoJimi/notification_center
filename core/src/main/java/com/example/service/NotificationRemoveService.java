package com.example.service;


import com.example.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class NotificationRemoveService {

    private final NotificationRepository repository;

    public void deleteById(String id){
        log.info("deleted: {}", id);
        repository.deleteById(id);
    }
}
