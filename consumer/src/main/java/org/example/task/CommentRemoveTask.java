package org.example.task;

import com.example.*;
import lombok.extern.slf4j.Slf4j;
import org.example.event.comment.CommentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

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
