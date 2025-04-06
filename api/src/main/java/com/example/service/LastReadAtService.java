package com.example.service;

import com.example.repository.NotificationReadRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@AllArgsConstructor
public class LastReadAtService {

    private final NotificationReadRepository repository;

    public Instant setLastReadAt(long userId){
        return repository.setLastReadAt(userId);
    }

    public Instant getLastReadAt(long userId){
        return repository.getLastReadAt(userId);
    }
}
