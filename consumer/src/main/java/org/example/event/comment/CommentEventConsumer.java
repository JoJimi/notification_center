package org.example.event.comment;

import lombok.extern.slf4j.Slf4j;
import org.example.event.comment.CommentEvent;
import org.example.task.CommentAddTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
public class CommentEventConsumer {

    @Autowired
    CommentAddTask commentAddTask;

    @Bean("comment")
    public Consumer<CommentEvent> comment() {
        return event -> {
            if(event.getType() == CommentEventType.ADD){
                commentAddTask.processEvent(event);
            }
        }
    }
}
