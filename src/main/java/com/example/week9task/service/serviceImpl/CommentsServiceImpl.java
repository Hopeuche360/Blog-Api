package com.example.week9task.service.serviceImpl;

import com.example.week9task.dto.ApiResponse;
import com.example.week9task.dto.LikeCommentResponseDto;
import com.example.week9task.exception.AppException;
import com.example.week9task.exception.ResourceNotFoundException;
import com.example.week9task.model.Comments;
import com.example.week9task.model.Posts;
import com.example.week9task.model.User;
import com.example.week9task.repository.CommentsRepository;
import com.example.week9task.repository.PostsRepository;
import com.example.week9task.repository.UserRepository;
import com.example.week9task.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentsServiceImpl implements CommentsService {


    private final PostsRepository postsRepository;
    private final CommentsRepository commentsRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentsServiceImpl(PostsRepository postsRepository,
                               CommentsRepository commentsRepository,
                               UserRepository userRepository) {
        this.postsRepository = postsRepository;
        this.commentsRepository = commentsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> createComment(long userId, long postId, String comment) {
        Optional<Posts> posts = postsRepository.findById(postId);
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent() && posts.isPresent() && !comment.isBlank()) {
            Comments comments = new Comments();
            comments.setContent(comment);
            Comments comments1 = commentsRepository.save(comments);
            posts.get().getComments().add(comments1);
            Posts posts1 = postsRepository.save(posts.get());
            user.get().getComments().add(comments1);
            userRepository.save(user.get());
            ApiResponse response = new ApiResponse();
            response.setMessage("Comment created");
            response.setData(posts1);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
        } if (user.isPresent() && posts.isEmpty()) {
            throw new ResourceNotFoundException("post", "postId", postId);
        } if (user.isEmpty() && posts.isPresent()) {
            throw  new ResourceNotFoundException("user", "userId", userId);
        } throw new AppException("Content cannot be blank", HttpStatus.NO_CONTENT);
    }

    @Override
    public List<Comments> getAllCommentsOnAPost(long postId) {
        Optional<Posts> posts = postsRepository.findById(postId);
        if (posts.get().getComments().isEmpty()) {
            throw new ResourceNotFoundException("Posts", "postId", postId);
        } else{
            return posts.get().getComments();
        }
    }

    @Override
    public Comments editComment(long postId, long commentId, String content) {
        Posts existingPosts = postsRepository.findById(postId).orElseThrow(()->
                new ResourceNotFoundException("Posts", "postId", postId));
        Comments existingComments = commentsRepository.findById(commentId).orElseThrow(()->
                new ResourceNotFoundException("Comments", "commentId", commentId));
        if (existingPosts.getComments().contains(existingComments)) {
            existingComments.setContent(content);
            commentsRepository.save(existingComments);
            return existingComments;
        } throw new ResourceNotFoundException("Comments", "content", content);
    }

//    @Override
//    public ResponseEntity<?> likeComment(long commentId) {
//        LikeCommentResponseDto likeCommentResponseDto = new LikeCommentResponseDto();
//        Comments comments = commentsRepository.findById(commentId).orElseThrow(()->
//                new ResourceNotFoundException("Comments", "commentId", commentId));
//        if (comments.getLikeQuantity() == 1) {
//            comments.setLikeQuantity(comments.getLikeQuantity() - 1);
//            likeCommentResponseDto.setLike(false);
//        } else {
//            comments.setLikeQuantity(comments.getLikeQuantity() + 1);
//            likeCommentResponseDto.setLike(true);
//        }
//        Comments comments1 = commentsRepository.save(comments);
//        likeCommentResponseDto.setComments(comments1);
//        return new ResponseEntity<>(likeCommentResponseDto, HttpStatus.OK);
//    }
}
