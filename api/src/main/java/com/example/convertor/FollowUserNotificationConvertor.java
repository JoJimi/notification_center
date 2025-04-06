package com.example.convertor;

import com.example.client.UserClient;
import com.example.domain.follow.FollowNotification;
import com.example.domain.user.User;
import com.example.dto.convert.ConvertedFollowNotification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FollowUserNotificationConvertor {

    private final UserClient userClient;

    public ConvertedFollowNotification convert(FollowNotification notification){
        User user = userClient.getUser(notification.getFollowerId());
        boolean isFollowing = userClient.getIsFollowing(notification.getUserId(), notification.getFollowerId());

        return new ConvertedFollowNotification(
                notification.getId(),
                notification.getType(),
                notification.getOccurredAt(),
                notification.getLastUpdateAt(),
                user.getName(),
                user.getProfileImageUrl(),
                isFollowing
        );
    }
}
