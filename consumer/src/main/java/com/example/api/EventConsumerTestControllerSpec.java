package com.example.api;

import com.example.event.comment.CommentEvent;
import com.example.event.follow.FollowEvent;
import com.example.event.like.LikeEvent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;

@Tag(name = "Event Consumer 호출 테스트 API")
public interface EventConsumerTestControllerSpec {
    @Operation(
        requestBody = @RequestBody(
                content = {
                        @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = {
                                    @ExampleObject(name = "댓글 이벤트", value = COMMENT_EVENT_PAYLOAD)
                            }
                        )
                }
        )
    )
    void comment(CommentEvent event);

    String COMMENT_EVENT_PAYLOAD = """
            {
                "type": "ADD",
                "postId": 1,
                "userId": 2,
                "commentId": 3,
            }
            """;

    @Operation(
            requestBody = @RequestBody(
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = {
                                            @ExampleObject(name = "게시물 좋아요 이벤트", value = LIKE_EVENT_PAYLOAD)
                                    }
                            )
                    }
            )
    )
    void like(LikeEvent event);

    String LIKE_EVENT_PAYLOAD = """
            {
                "type": "ADD",
                "postId": 1,
                "userId": 2,
                "createAt": "2024-08-08T18:25:43:511Z"
            }
            """;

    @Operation(
            requestBody = @RequestBody(
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = {
                                            @ExampleObject(name = "게시물 팔로우 이벤트", value = FOLLOW_EVENT_PAYLOAD)
                                    }
                            )
                    }
            )
    )
    void follow(FollowEvent event);

    String FOLLOW_EVENT_PAYLOAD = """
            {
                "type": "ADD",
                "userId": 2,
                "targetUserId": 1,
                "createAt": "2024-08-08T18:25:43:511Z"
            }
            """;
}
