package com.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class SetLastReadAtResponse {
    private Instant lastReadAt;
}
