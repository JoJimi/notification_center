package com.example.controller.test;

import com.example.event.comment.CommentEvent;
import com.example.event.follow.FollowEvent;
import com.example.event.like.LikeEvent;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Consumer;

@RestController
@AllArgsConstructor
public class EventConsumerTestController implements EventConsumerTestControllerSpec{

    private final Consumer<CommentEvent> comment;
    private final Consumer<LikeEvent> like;
    private final Consumer<FollowEvent> follow;

    @Override
    @PostMapping("/test/comment")
    public void comment(@RequestBody CommentEvent event){
        comment.accept(event);
    }

    // LikeEventConsumer에서 Bean 처리한 like() 함수가 실행된다
    @Override
    @PostMapping("/test/like")
    public void like(@RequestBody LikeEvent event){
        like.accept(event);
    }

    @Override
    @PostMapping("/test/follow")
    public void follow(@RequestBody FollowEvent event){
        follow.accept(event);
    }
}
