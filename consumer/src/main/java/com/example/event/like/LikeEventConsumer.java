package com.example.event.like;

import com.example.task.like.LikeAddTask;
import com.example.task.like.LikeRemoveTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

import static com.example.event.like.LikeEventType.*;

@Slf4j
@Component
public class LikeEventConsumer {

    @Autowired
    private LikeAddTask likeAddTask;

    @Autowired
    private LikeRemoveTask likeRemoveTask;

    @Bean("like")
    public Consumer<LikeEvent> like() {
        return event -> {
            if(event.getType() == ADD){
                likeAddTask.processEvent(event);
            }else if(event.getType() == REMOVE){
                likeRemoveTask.processEvent(event);
            }
        };
    }
}
