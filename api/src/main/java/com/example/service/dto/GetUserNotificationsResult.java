package com.example.service.dto;

import com.example.service.dto.convert.ConvertedNotification;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetUserNotificationsResult {
    private List<ConvertedNotification> notifications;
    private boolean hasNext;
}
