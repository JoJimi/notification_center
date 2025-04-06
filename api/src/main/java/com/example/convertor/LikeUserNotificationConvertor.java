package com.example.convertor;

import com.example.client.PostClient;
import com.example.client.UserClient;
import com.example.domain.like.LikeNotification;
import com.example.domain.post.Post;
import com.example.domain.user.User;
import com.example.dto.convert.ConvertedLikeNotification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LikeUserNotificationConvertor {

    private final UserClient userClient;
    private final PostClient postClient;

    public ConvertedLikeNotification convert(LikeNotification notification){
        User user = userClient.getUser(notification.getLikerIds().getLast());
        Post post = postClient.getPost(notification.getPostId());

        return new ConvertedLikeNotification(
                notification.getId(),
                notification.getType(),
                notification.getOccurredAt(),
                notification.getLastUpdateAt(),
                user.getName(),
                user.getProfileImageUrl(),
                notification.getLikerIds().size(),
                post.getImageUrl()
        );
    }
}
