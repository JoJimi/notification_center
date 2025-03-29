package org.example.event.comment;

import lombok.extern.slf4j.Slf4j;
import org.example.event.comment.CommentEvent;
import org.example.task.CommentAddTask;
import org.example.task.CommentRemoveTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

import static org.example.event.comment.CommentEventType.*;

@Slf4j
@Component
public class CommentEventConsumer {

    @Autowired
    CommentAddTask commentAddTask;

    @Autowired
    CommentRemoveTask commentRemoveTask;

    @Bean("comment")
    public Consumer<CommentEvent> comment() {
        return event -> {
            if(event.getType() == ADD){
                commentAddTask.processEvent(event);
            }else if(event.getType() == REMOVE){
                commentRemoveTask.processEvent(event);
            }
        };
    }
}
