package com.example.task.like;

import com.example.client.PostClient;
import com.example.domain.like.LikeNotification;
import com.example.domain.notification.Notification;
import com.example.domain.notification.NotificationType;
import com.example.domain.post.Post;
import com.example.event.like.LikeEvent;
import com.example.service.NotificationGetService;
import com.example.service.NotificationSaveService;
import com.example.utils.NotificationIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class LikeAddTask {

    @Autowired
    private PostClient postClient;

    @Autowired
    private NotificationGetService getService;

    @Autowired
    private NotificationSaveService saveService;

    public void processEvent(LikeEvent event){
        Post post = postClient.getPost(event.getPostId());
        // 게시물이 없는 경우
        if(post == null){
            log.error("Post is null with postId: {}", event.getPostId());
            return;
        }
        // 본인 게시물에 좋아요를 누른 경우
        if(post.getUserId().equals(event.getUserId())){
            return;
        }

        saveService.upsert(createOrUpdateNotification(post, event));

    }
    private Notification createOrUpdateNotification(Post post, LikeEvent event){
        Optional<Notification> optionalNotification = getService.getNotificationByTypeAndPostId(NotificationType.LIKE, post.getId());

        Instant now = Instant.now();
        Instant retention = now.plus(90, ChronoUnit.DAYS);

        if(optionalNotification.isPresent()){
            // 업데이트
            return updataNotification((LikeNotification)optionalNotification.get(), event, now, retention);
        }else {
            // 신규생성
            return createNotification(post, event, now, retention);
        }
    }

    private Notification updataNotification(LikeNotification notification, LikeEvent event, Instant now, Instant retention){
        notification.addLiker(event.getUserId(), event.getCreatedAt(), now, retention);
        return notification;
    }

    private Notification createNotification(Post post, LikeEvent event, Instant now, Instant retention){
        return new LikeNotification(
                NotificationIdGenerator.generate(),
                post.getUserId(),
                NotificationType.LIKE,
                event.getCreatedAt(),
                now,
                now,
                retention,
                post.getId(),
                List.of(event.getUserId())
        );
    }
}
