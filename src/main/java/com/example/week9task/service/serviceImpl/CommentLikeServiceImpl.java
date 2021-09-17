package com.example.week9task.service.serviceImpl;

import com.example.week9task.exception.AppException;
import com.example.week9task.exception.ResourceNotFoundException;
import com.example.week9task.model.CommentLikes;
import com.example.week9task.model.Comments;
import com.example.week9task.model.Posts;
import com.example.week9task.model.User;
import com.example.week9task.repository.CommentLikeRepository;
import com.example.week9task.repository.CommentsRepository;
import com.example.week9task.repository.PostsRepository;
import com.example.week9task.repository.UserRepository;
import com.example.week9task.service.CommentLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CommentLikeServiceImpl implements CommentLikeService {

    private final UserRepository userRepository;
    private final PostsRepository postsRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final CommentsRepository commentsRepository;

    @Autowired
    public CommentLikeServiceImpl(UserRepository userRepository, PostsRepository postsRepository,
                                  CommentLikeRepository commentLikeRepository, CommentsRepository commentsRepository) {
        this.userRepository = userRepository;
        this.postsRepository = postsRepository;
        this.commentLikeRepository = commentLikeRepository;
        this.commentsRepository = commentsRepository;
    }

    @Override
    public ResponseEntity<Comments> likeAndUnlikeComments(long userId, long postId, long commentId) {
        Optional<User> user1 = userRepository.findById(userId);
        if (user1.isEmpty()) {
            throw new AppException("Invalid User, please login", HttpStatus.BAD_REQUEST);
        }
        Optional<Posts> post1 = postsRepository.findById(postId);
        if (post1.isEmpty()) {
            throw new AppException("Post with " + postId +" does not exist", HttpStatus.BAD_REQUEST);
        }
        Optional<Comments> comment = commentsRepository.findById(commentId);
        if (comment.isEmpty()) {
            throw new AppException("Comment with " + commentId + "does not exist", HttpStatus.BAD_REQUEST);
        }
        Optional<CommentLikes> commentLikes = commentLikeRepository.findCommentLikesByUserAndComments(user1.get(), comment.get());
        Set<CommentLikes> commentLikesSet;
        if (commentLikes.isEmpty()) {
            CommentLikes commentLikes1 = new CommentLikes();
            commentLikes1.setComments(comment.get());
            commentLikes1.setUser(user1.get());
            commentLikesSet = comment.get().getCommentLikes();
            commentLikesSet.add(commentLikes1);
            comment.get().setLikeQuantity(commentLikesSet.size());
            commentLikeRepository.save(commentLikes1);
        } else {
            commentLikesSet = comment.get().getCommentLikes();
            commentLikesSet.remove(commentLikes.get());
            commentLikeRepository.delete(commentLikes.get());
            comment.get().setLikeQuantity(commentLikesSet.size());
        }
        Comments comments = commentsRepository.save(comment.get());
        return new ResponseEntity<Comments>(comments, HttpStatus.CREATED);
    }
}

