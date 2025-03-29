package com.example.task;

import com.example.client.PostClient;
import com.example.domain.NotificationType;
import com.example.domain.Post;
import com.example.event.comment.CommentEvent;
import com.example.service.NotificationGetService;
import com.example.service.NotificationRemoveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class CommentRemoveTask {

    @Autowired
    PostClient postClient;

    @Autowired
    NotificationGetService getService;

    @Autowired
    NotificationRemoveService removeService;

    public void processEvent(CommentEvent event){
        Post post = postClient.getPost(event.getPostId());
        if(Objects.equals(post.getUserId(), event.getUserId())){
            return;
        }

        getService.getNotification(NotificationType.COMMENT, event.getCommentId())
                .ifPresentOrElse(
                        notification -> removeService.deleteById(notification.getId()),
                                () -> log.error("Notification not found")
                );
    }
}
