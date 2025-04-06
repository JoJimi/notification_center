package com.example.controller;

import com.example.dto.response.SetLastReadAtResponse;
import com.example.service.LastReadAtService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/user-notification")
public class NotificationReadController implements NotificationReadControllerSpec{
    private final LastReadAtService service;

    @Override
    @PutMapping("/{userId}/read")
    public SetLastReadAtResponse setLastReadAt(
            @PathVariable(value = "userId") Long userId){

        Instant lastReadAt = service.setLastReadAt(userId);
        return new SetLastReadAtResponse(lastReadAt);
    }
}
