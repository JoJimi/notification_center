package com.example.notification.repository;

import com.example.notification.entity.Notification;
import com.example.notification.type.NotificationType;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.jupiter.api.Assertions.*;

class NotificationRepositoryMemoryImplTest {

    private final NotificationRepositoryMemoryImpl sut = new NotificationRepositoryMemoryImpl();
    private final Instant now = Instant.now();
    private final Instant deletedAt = Instant.now().plus(90, DAYS);

    @Test
    void test_save() {
        sut.save(new Notification("1", 2L, NotificationType.LIKE, now, deletedAt));
        Optional<Notification> notification = sut.findById("1");

        assertTrue(notification.isPresent());
    }

    @Test
    void test_find_by_id(){
        sut.save(new Notification("2", 2L, NotificationType.LIKE, now, deletedAt));
        Optional<Notification> optionalNotification = sut.findById("2");

        Notification notification = optionalNotification.orElseThrow();
        assertEquals(notification.id, "2");
        assertEquals(notification.userId, 2L);
        assertEquals(notification.createdAt, now);
        assertEquals(notification.deletedAt, deletedAt);

    }

    @Test
    void test_delete_by_id(){
        sut.save(new Notification("3", 2L, NotificationType.LIKE, now, deletedAt));
        sut.deleteById("3");

        Optional<Notification> optionalNotification = sut.findById("3");
        assertFalse(optionalNotification.isPresent());
    }

}