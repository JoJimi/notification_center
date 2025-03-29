package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@AllArgsConstructor
@Getter
public class Comment {
    private Long id;
    private Long userId;
    private String content;
    private Instant createdAt;
}
