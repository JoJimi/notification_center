package com.example.event.comment;

import com.example.task.comment.CommentAddTask;
import lombok.extern.slf4j.Slf4j;
import com.example.task.comment.CommentRemoveTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

import static com.example.event.comment.CommentEventType.*;

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
