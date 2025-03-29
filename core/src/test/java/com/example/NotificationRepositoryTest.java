package com.example;

import com.example.domain.CommentNotification;
import com.example.domain.Notification;
import com.example.domain.NotificationType;
import com.example.repository.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootApplication
@SpringBootTest
class NotificationRepositoryTest {

    @Autowired
    private NotificationRepository sut;

    private final Long userId = 2L;
    private final Long commentId = 1L;
    private final Long writerId = 1L;
    private final Long postId = 1L;
    private final Instant now = Instant.now();
    private final Instant ninetyDaysAfter = Instant.now().plus(90, DAYS);

    @Test
    void test_save() {
        String id = "1";

        sut.save(new CommentNotification(id, userId, NotificationType.COMMENT, now, now, now, ninetyDaysAfter, postId, writerId, "comment1", commentId));

        Optional<Notification> optionalNotification = sut.findById(id);

        assertTrue(optionalNotification.isPresent());
    }

    @Test
    void test_find_by_id(){
        String id = "2";

        sut.save(new CommentNotification(id, userId, NotificationType.COMMENT, now, now, now, ninetyDaysAfter, postId, writerId, "comment1", commentId));
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

        sut.save(new CommentNotification(id, userId, NotificationType.COMMENT, now, now, now, ninetyDaysAfter, postId, writerId, "comment1", commentId));
        sut.deleteById(id);

        Optional<Notification> optionalNotification = sut.findById(id);
        assertFalse(optionalNotification.isPresent());
    }

}