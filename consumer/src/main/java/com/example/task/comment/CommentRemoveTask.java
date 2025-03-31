package com.example.task.comment;

import com.example.client.PostClient;
import com.example.domain.notification.NotificationType;
import com.example.domain.post.Post;
import com.example.event.comment.CommentEvent;
import com.example.service.NotificationGetService;
import com.example.service.NotificationRemoveService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.example.domain.notification.NotificationType.*;

@Slf4j
@Component
@AllArgsConstructor
public class CommentRemoveTask {

    private final PostClient postClient;
    private final NotificationGetService getService;
    private final NotificationRemoveService removeService;

    public void processEvent(CommentEvent event){
        Post post = postClient.getPost(event.getPostId());
        if(Objects.equals(post.getUserId(), event.getUserId())){
            return;
        }

        getService.getNotificationByTypeAndCommentId(COMMENT, event.getCommentId())
                .ifPresent(
                        notification -> removeService.deleteById(notification.getId())
                );
    }
}
