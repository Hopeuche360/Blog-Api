package com.example.week9task.service.serviceImpl;

import com.example.week9task.dto.ApiResponse;
import com.example.week9task.exception.AppException;
import com.example.week9task.exception.ResourceNotFoundException;
import com.example.week9task.model.Posts;
import com.example.week9task.model.User;
import com.example.week9task.repository.PostsRepository;
import com.example.week9task.repository.UserRepository;
import com.example.week9task.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class PostsServiceImpl implements PostsService {

    private final PostsRepository postsRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostsServiceImpl(PostsRepository postsRepository, UserRepository userRepository) {
        this.postsRepository = postsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> createPost(Long userId, String content) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent() && !content.isBlank()){
            Posts posts = new Posts();
            posts.setContent(content);
//            posts.setUser(user.get());
            Posts posts1 = postsRepository.save(posts);
            user.get().getPosts().add(posts1);
            userRepository.save(user.get());
            ApiResponse response = new ApiResponse();
            response.setMessage("Post created successfully");
            response.setData(user.get());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } if (user.isPresent() && content.isBlank()) {
            throw new AppException("Content cannot be blank", HttpStatus.NO_CONTENT);
        } throw new ResourceNotFoundException("user", "userid", userId );
    }

    @Override
    public List<Posts> getAllPosts() {
        return postsRepository.findAll();
    }

    @Override
    public Posts getPostById(long userId, long postId) {
        User existingUser = userRepository.findById(userId).orElseThrow(()->
                new ResourceNotFoundException("User", "userId", userId));
        Optional<Posts> existingPost = postsRepository.findById(postId);
        if (existingUser.getPosts().contains(existingPost)) {
            return existingPost.get();
        } else throw new ResourceNotFoundException("Post", "postId", postId);
    }


    @Override
    public Posts editPost(String content, long userId, long postId) {
        Posts existingPost = postsRepository.findById(postId).orElseThrow(()->
                new ResourceNotFoundException("Post", "postId", postId));
        User existingUser = userRepository.findById(userId).orElseThrow(()->
                new ResourceNotFoundException("User", "userId", userId));

        if (existingUser.getPosts().contains(existingPost)) {
            existingPost.setContent(content);
            postsRepository.save(existingPost);
            return existingPost;
        } throw new ResourceNotFoundException("Post", "content", content);
    }

    @Override
    public ResponseEntity<?> addPostToListOfFavorites(long userId, long postId) {
        User user = userRepository.findById(userId).orElseThrow(()->
                new ResourceNotFoundException("user", "userId", userId));
        Posts posts = postsRepository.findById(postId).orElseThrow(()->
                new ResourceNotFoundException("post", "postId", postId));
            user.getFavorites().add(posts);

        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> removePostFromListOfFavorites(long userId, long postId) {
        User user = userRepository.findById(userId).orElseThrow(()->
                new ResourceNotFoundException("user", "userId", userId));
        Posts post = postsRepository.findById(postId).orElseThrow(()->
                new ResourceNotFoundException("post", "postId", postId));
        user.getFavorites().remove(post);
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }

    @Override
    public Set<Posts> getAllFavoritePostsOfAUser(long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->
                new ResourceNotFoundException("user", "userId", userId));
        return user.getFavorites();
    }

    @Override
    public void deleteUserPostById(long userId, long postId) {
        User user = userRepository.findById(userId).orElseThrow(()->
                new ResourceNotFoundException("user", "userId", userId));
        Posts posts = postsRepository.findById(postId).orElseThrow(()->
                new ResourceNotFoundException("post", "postId", postId));
        if (user.getPosts().contains(posts)) {
            postsRepository.deleteById(postId);
        }
    }
}
