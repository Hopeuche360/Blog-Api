package com.example.week9task.controller;

import com.example.week9task.service.CommentLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/like/comment")
public class CommentLikeController {
    private final CommentLikeService commentLikeService;

    @Autowired
    public CommentLikeController(CommentLikeService commentLikeService) {
        this.commentLikeService = commentLikeService;
    }

    @PostMapping("/{userId}/{postId}/{commentId}")
    public ResponseEntity<?> likeAndUnlikeComments(@PathVariable(value = "userId") long userId,
                                                          @PathVariable(value = "postId") long postId,
                                                          @PathVariable(value = "commentId") long commentId) {
        return new ResponseEntity<>(commentLikeService.likeAndUnlikeComments(userId, postId, commentId), HttpStatus.CREATED);
    }
}
