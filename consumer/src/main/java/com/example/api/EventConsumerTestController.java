package com.example.api;

import com.example.event.comment.CommentEvent;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Consumer;

@RestController
public class EventConsumerTestController implements EventConsumerTestControllerSpec{

    @Autowired
    private Consumer<CommentEvent> comment;

    @Override
    @PostMapping("/test/comment")
    public void comment(@RequestBody CommentEvent event){
        comment.accept(event);
    }
}
