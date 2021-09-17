package com.example.week9task.service;

import com.example.week9task.model.PostLikes;
import com.example.week9task.model.Posts;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface PostLikeService {
    ResponseEntity<Posts> likeAndUnlikePost(long postId, long userId);
}
