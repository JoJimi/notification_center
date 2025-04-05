package com.example.repository;

import com.example.IntegrationTest;
import com.example.domain.comment.CommentNotification;
import com.example.domain.notification.Notification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.time.Instant;
import java.util.Optional;

import static com.example.domain.notification.NotificationType.*;
import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.jupiter.api.Assertions.*;


class NotificationRepositoryTest extends IntegrationTest {

    @Autowired
    private NotificationRepository sut;

    private final Long userId = 2L;
    private final Long postId = 3L;
    private final Long writerId = 4L;
    private final Long commentId = 5L;
    private final String comment = "comment";
    private final Instant now = Instant.now();
    private final Instant ninetyDaysAfter = Instant.now().plus(90, DAYS);

    @BeforeEach
    void setUp(){
        for(int i = 1; i <= 5; i++){
            Instant occurredAt = now.minus(i, DAYS);
            sut.save(new CommentNotification("id-" + i, userId, COMMENT, occurredAt, now, now, ninetyDaysAfter, postId, writerId, comment, commentId));
        }
    }

    @AfterEach
    void tearDown(){
        sut.deleteAll();
    }

    @Test
    void test_save() {
        String id = "1";

        sut.save(createCommentNotification(id));
        Optional<Notification> optionalNotification = sut.findById(id);

        assertTrue(optionalNotification.isPresent());
    }

    @Test
    void test_find_by_id(){
        String id = "2";

        sut.save(createCommentNotification(id));
        Optional<Notification> optionalNotification = sut.findById(id);
        Notification notification = optionalNotification.orElseThrow();

        assertEquals(notification.getId(), id);
        assertEquals(notification.getUserId(), userId);
        assertEquals(notification.getOccurredAt().getEpochSecond(), now.getEpochSecond());
        assertEquals(notification.getCreatedAt().getEpochSecond(), now.getEpochSecond());
        assertEquals(notification.getLastUpdateAt().getEpochSecond(), now.getEpochSecond());
        assertEquals(notification.getDeletedAt().getEpochSecond(), ninetyDaysAfter.getEpochSecond());

    }

    @Test
    void test_delete_by_id(){
        String id = "3";

        sut.save(createCommentNotification(id));
        sut.deleteById(id);
        Optional<Notification> optionalNotification = sut.findById(id);

        assertFalse(optionalNotification.isPresent());
    }

    @Test
    void test_findAllByUserIdOrderByOccurredAtDesc(){
        Pageable pageable = PageRequest.of(0, 3);

        Slice<Notification> result = sut.findAllByUserIdOrderByOccurredAtDesc(userId, pageable);

        assertEquals(3, result.getContent().size());
        assertTrue(result.hasNext());

        Notification first = result.getContent().get(0);
        Notification second = result.getContent().get(1);
        Notification third = result.getContent().get(2);

        assertTrue(first.getOccurredAt().isAfter(second.getOccurredAt()));
        assertTrue(second.getOccurredAt().isAfter(third.getOccurredAt()));
    }

    @Test
    void test_findAllByUserIdAndOccurredAtLessThanOrderByOccurredAtDesc_firstQuery(){
        Pageable pageable = PageRequest.of(0, 3);

        Slice<Notification> firstResult = sut.findAllByUserIdAndOccurredAtLessThanOrderByOccurredAtDesc(userId, now, pageable);

        assertEquals(3, firstResult.getContent().size());
        assertTrue(firstResult.hasNext());

        Notification first = firstResult.getContent().get(0);
        Notification second = firstResult.getContent().get(1);
        Notification third = firstResult.getContent().get(2);

        assertTrue(first.getOccurredAt().isAfter(second.getOccurredAt()));
        assertTrue(second.getOccurredAt().isAfter(third.getOccurredAt()));
    }

    @Test
    void test_findAllByUserIdAndOccurredAtLessThanOrderByOccurredAtDesc_secondQueryWithPivot(){
        Pageable pageable = PageRequest.of(0, 3);

        Slice<Notification> firstResult = sut.findAllByUserIdAndOccurredAtLessThanOrderByOccurredAtDesc(userId, now, pageable);
        Notification last = firstResult.getContent().get(2);

        Instant pivot = last.getOccurredAt();
        Slice<Notification> secondResult = sut.findAllByUserIdAndOccurredAtLessThanOrderByOccurredAtDesc(userId, pivot, pageable);

        assertEquals(2, secondResult.getContent().size());
        assertFalse(secondResult.hasNext());

        Notification first = secondResult.getContent().get(0);
        Notification second = secondResult.getContent().get(1);

        assertTrue(first.getOccurredAt().isAfter(second.getOccurredAt()));
    }

    private CommentNotification createCommentNotification(String id){
        return new CommentNotification(id, userId, LIKE, now, now, now, ninetyDaysAfter, postId, writerId, comment, commentId);
    }
}