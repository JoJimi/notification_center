package com.example.client;

import com.example.domain.comment.Comment;
import com.example.domain.user.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserClient {
    private final Map<Long, User> users = new HashMap<>();

    public UserClient(){
        users.put(1L, new User(1L, "user 1", "profileImageUrl1"));
        users.put(2L, new User(2L, "user 2", "profileImageUrl2"));
        users.put(3L, new User(3L, "user 3", "profileImageUrl3"));
        users.put(4L, new User(4L, "user 4", "profileImageUrl4"));
        users.put(5L, new User(5L, "user 5", "profileImageUrl5"));

    }

    public User getUser(Long id){
        return users.get(id);
    }

    public Boolean getIsFollowing(Long followerId, Long followedId){
        return true;
    }
}
