package com.example.week9task.controller;

import com.example.week9task.model.Posts;
import com.example.week9task.service.PostsService;
import com.example.week9task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostsService postsService;
    private final UserService userService;

    @Autowired
    public PostController(PostsService postsService, UserService userService) {
        this.postsService = postsService;
        this.userService = userService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> createPost(@PathVariable(value = "userId") long userId,
                                        @RequestBody String content, HttpSession httpSession) {
        return postsService.createPost(userId, content, httpSession);
    }

    @GetMapping("/get")
    public List<Posts> getAllPosts() {
        return postsService.getAllPosts();
    }

    @GetMapping("/get/{userId}/{postId}")
    public ResponseEntity<Posts> getPostById(@PathVariable(value = "userId") long userId,
                                             @PathVariable(value = "postId") long postId) {
        return new ResponseEntity<Posts>(postsService.getPostById(userId, postId), HttpStatus.OK);
    }

    @PostMapping("/edit/{userId}/{postId}")
    public ResponseEntity<?> editPost(@RequestBody String content,
                                          @PathVariable(value = "userId") long userId,
                                          @PathVariable(value = "postId") long postId,
                                          HttpSession httpSession) {
        return new ResponseEntity<>(postsService.editPost(content, userId, postId, httpSession), HttpStatus.OK);
    }

    @PostMapping("/add-favorite/{userId}/{postId}")
    public ResponseEntity<?> addPostToListOfFavorites(@PathVariable(value = "userId") long userId,
                                                      @PathVariable(value = "postId") long postId) {
        return new ResponseEntity<>(postsService.addPostToListOfFavorites(userId, postId), HttpStatus.OK);
    }

    @PostMapping("/remove-favorite/{userId}/{postId}")
    public ResponseEntity<?> removePostFromListOfFavorites(@PathVariable(value = "userId") long userId,
                                                      @PathVariable(value = "postId") long postId) {
        return new ResponseEntity<>(postsService.removePostFromListOfFavorites(userId, postId), HttpStatus.OK);
    }

    @GetMapping("/get-favorites/{userId}")
    public ResponseEntity<?> getListOfFavoritePostsOfUser(@PathVariable(value = "userId") long userId) {
        return  new ResponseEntity<>(postsService.getAllFavoritePostsOfAUser(userId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}/{postId}")
    public ResponseEntity<String> removeUserPostById(@PathVariable(value = "userId") long userId,
                                                     @PathVariable(value = "postId") long postId) {
        postsService.deleteUserPostById(userId, postId);
        return new ResponseEntity<String>("User's post removed successfully", HttpStatus.OK);
    }

}
