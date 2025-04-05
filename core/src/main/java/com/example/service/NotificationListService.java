package com.example.service;

import com.example.domain.notification.Notification;
import com.example.dto.GetUserNotificationsByPivotResult;
import com.example.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
public class NotificationListService {

    private final NotificationRepository repository;

    /**
     * 목록 조회: Pivot 방식 (기준점: occurredAt, size) vs Paging 방식 (page size, page number)
     * 새로운 데이터가 추가 되면 Paging 방식은 순서 보장이 안된다.
     * 따라서 실시간 성이 강한 부분에서는 Paging 방식을 사용하지 않고, Pivot 방식을 사용된다.
     * 또한 Pivot 방식이 보다 더 성능이 좋다.
     */
    public GetUserNotificationsByPivotResult getUserNotificationsByPivot(Long userId, Instant occurredAt){
        Slice<Notification> result;

        if(occurredAt == null){
            result = repository.findAllByUserIdOrderByOccurredAtDesc(userId, PageRequest.of(0, PAGE_SIZE));
        }
        else {
            result = repository.findAllByUserIdAndOccurredAtLessThanOrderByOccurredAtDesc(userId, occurredAt, PageRequest.of(0, PAGE_SIZE));
        }
        return new GetUserNotificationsByPivotResult(
                result.toList(),
                result.hasNext()
        );
    }

    // 클라이언트와 협의가 필요한 부분
    private static final int PAGE_SIZE = 20;
}
