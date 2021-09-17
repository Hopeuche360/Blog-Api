package com.example.week9task.controller;

import com.example.week9task.model.Comments;
import com.example.week9task.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentsController {

    private final CommentsService commentsService;

    @Autowired
    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @PostMapping("{userId}/{postId}")
    public ResponseEntity<?> createComment(@PathVariable(value = "userId") long userId,
                                           @PathVariable(value = "postId") long postId,
                                           @RequestBody String content) {
        return commentsService.createComment(userId, postId, content);
    }

    @GetMapping("/get/{postId}")
    public ResponseEntity<?> getAllCommentsOnAPost(@PathVariable(value = "postId") long postId) {
        return new ResponseEntity<>(commentsService.getAllCommentsOnAPost(postId), HttpStatus.OK);
    }

    @PutMapping("/edit/{postId}/{commentId}")
    public ResponseEntity<?> editComment(@PathVariable(value = "postId") long postId,
                                         @PathVariable(value = "commentId") long commentId,
                                         @RequestBody String content) {
        return new ResponseEntity<>(commentsService.editComment(postId, commentId, content), HttpStatus.OK);
    }
}
