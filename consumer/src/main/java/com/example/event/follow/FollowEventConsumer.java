package com.example.event.follow;

import com.example.task.follow.FollowAddTask;
import com.example.task.follow.FollowRemoveTask;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

import static com.example.event.follow.FollowEventType.*;

@Slf4j
@Component
@AllArgsConstructor
public class FollowEventConsumer {

    private final FollowAddTask followAddTask;
    private final FollowRemoveTask followRemoveTask;

    @Bean("follow")
    public Consumer<FollowEvent> follow() {
        return event -> {
            if(event.getType() == ADD){
                followAddTask.processEvent(event);
            }
            else if(event.getType() == REMOVE){
                followRemoveTask.processEvent(event);
            }
        };
    }
}
