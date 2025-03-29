package org.example.task;

import com.NotificationIdGenerator;
import com.example.*;
import lombok.extern.slf4j.Slf4j;
import org.example.event.comment.CommentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Slf4j
@Component
public class CommentAddTask {

    @Autowired
    PostClient postClient;

    @Autowired
    CommentClient commentClient;

    @Autowired
    NotificationSaveService saveService;

    public void processEvent(CommentEvent event){
        Post post = postClient.getPost(event.getPostId());
        if(Objects.equals(post.getUserId(), event.getUserId())){
            return;
        }

        Comment comment = commentClient.getComment(event.getCommentId());

        Notification notification = createNotification(post, comment);
        saveService.insert(notification);
    }

    private Notification createNotification(Post post, Comment comment){
        Instant now = Instant.now();

        return new CommentNotification(
                NotificationIdGenerator.generate(),
                post.getUserId(),
                NotificationType.COMMENT,
                comment.getCreatedAt(),
                now,
                now,
                now.plus(90, ChronoUnit.DAYS),
                post.getId(),
                comment.getUserId(),
                comment.getContent()
        );
    }
}
