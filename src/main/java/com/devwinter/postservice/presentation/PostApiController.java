package com.devwinter.postservice.presentation;

import com.devwinter.postservice.application.CreatePostFacade;
import com.devwinter.postservice.presentation.dto.BaseResponse;
import com.devwinter.postservice.presentation.dto.CreatePost;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostApiController {

    private final CreatePostFacade createPostFacade;

    @PostMapping("/{userId}")
    public BaseResponse<CreatePost.Response> createPost(
            @PathVariable Long userId,
            @Valid @RequestBody CreatePost.Request request) {
        createPostFacade.createPost(userId, request.toDto());
        return CreatePost.Response.success();
    }
}
