package com.example.week9task.service;

import com.example.week9task.model.Comments;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentsService {
    ResponseEntity<?> createComment(long userId, long postId, String comment);
    List<Comments> getAllCommentsOnAPost(long postId);
    Comments editComment(long postId, long commentId, String content);
}
