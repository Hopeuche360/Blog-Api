package com.example.week9task.controller;

import com.example.week9task.model.Posts;
import com.example.week9task.service.PostLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/like/posts")
public class PostLikeController {
    private final PostLikeService postLikeService;
    @Autowired
    public PostLikeController(PostLikeService postLikeService) {
        this.postLikeService = postLikeService;
    }

    @PostMapping("/{userId}/{postId}")
    public ResponseEntity<?> likeAndUnlikePost(@PathVariable(value = "userId") long userId,
                                          @PathVariable(value = "postId") long postId) {
        return new ResponseEntity<>(postLikeService.likeAndUnlikePost(postId, userId), HttpStatus.CREATED);
    }
}
