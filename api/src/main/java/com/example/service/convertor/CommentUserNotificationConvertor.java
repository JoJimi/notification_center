package com.example.service.convertor;

import com.example.client.PostClient;
import com.example.client.UserClient;
import com.example.domain.comment.CommentNotification;
import com.example.domain.post.Post;
import com.example.domain.user.User;
import com.example.service.dto.convert.ConvertedCommentNotification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentUserNotificationConvertor {

    private final UserClient userClient;
    private final PostClient postClient;

    public ConvertedCommentNotification convert(CommentNotification notification){
        User user = userClient.getUser(notification.getUserId());
        Post post = postClient.getPost(notification.getPostId());

        return new ConvertedCommentNotification(
                notification.getId(),
                notification.getType(),
                notification.getOccurredAt(),
                notification.getLastUpdateAt(),
                user.getName(),
                user.getProfileImageUrl(),
                notification.getComment(),
                post.getImageUrl()
        );
    }
}
