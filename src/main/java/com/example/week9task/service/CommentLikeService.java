package com.example.week9task.service;

import com.example.week9task.model.Comments;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CommentLikeService {
    ResponseEntity<Comments> likeAndUnlikeComments(long userId, long postId, long commentId);
}
