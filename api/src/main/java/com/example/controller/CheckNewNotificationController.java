package com.example.controller;

import com.example.dto.response.CheckNewNotificationResponse;
import com.example.service.CheckNewNotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/v1/user-notifications")
public class CheckNewNotificationController implements CheckNewNotificationControllerSpec{

    private final CheckNewNotificationService service;

    @Override
    @GetMapping("/{userId}/new")
    public CheckNewNotificationResponse checkNew(
            @PathVariable(value = "userId") Long userId
    ){
        boolean hasNew = service.checkNewNotification(userId);
        return new CheckNewNotificationResponse(hasNew);
    }
}
