package com.example.week9task.service;

import com.example.week9task.model.Posts;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@Service
public interface PostsService {
    ResponseEntity<?> createPost(Long userId, String content, HttpSession httpSession);
    List<Posts> getAllPosts();
    Posts getPostById(long userId, long postId);
    ResponseEntity<?> editPost(String content, long userId, long postId, HttpSession httpSession);
    ResponseEntity<?> addPostToListOfFavorites(long userId, long postId);
    ResponseEntity<?> removePostFromListOfFavorites(long userId, long postId);
    Set<Posts> getAllFavoritePostsOfAUser(long userId);
    void deleteUserPostById(long userId, long postId);
}
