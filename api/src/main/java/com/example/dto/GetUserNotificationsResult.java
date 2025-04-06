package com.example.dto;

import com.example.dto.convert.ConvertedNotification;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetUserNotificationsResult {
    private List<ConvertedNotification> notifications;
    private boolean hasNext;
}
