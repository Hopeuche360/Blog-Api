package com.example.week9task.service.serviceImpl;

import com.example.week9task.dto.LikePostResponseDto;
import com.example.week9task.exception.AppException;
import com.example.week9task.model.PostLikes;
import com.example.week9task.model.Posts;
import com.example.week9task.model.User;
import com.example.week9task.repository.CommentsRepository;
import com.example.week9task.repository.PostLikeRepository;
import com.example.week9task.repository.PostsRepository;
import com.example.week9task.repository.UserRepository;
import com.example.week9task.service.PostLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class PostLikeServiceImpl implements PostLikeService {
    private final PostLikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostsRepository postsRepository;

    @Autowired
    public PostLikeServiceImpl(PostLikeRepository likeRepository, UserRepository userRepository, PostsRepository postsRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.postsRepository = postsRepository;

    }

    @Override
    public ResponseEntity<Posts> likeAndUnlikePost(long postId, long userId) {

        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new AppException("Invalid User, please login", HttpStatus.BAD_REQUEST);
        }
        Optional<Posts> post = postsRepository.findById(postId);
        if(post.isEmpty()){
            throw new AppException("Post with " + postId +" does not exist", HttpStatus.BAD_REQUEST);
        }

        Optional<PostLikes> likes = likeRepository.findPostLikesByUserAndPosts(user.get(), post.get());
        Set<PostLikes> likesSet;
        if (likes.isEmpty()) {
            PostLikes like = new PostLikes();
            like.setPosts(post.get());
            like.setUser(user.get());
            likesSet = post.get().getPostLikes();
            likesSet.add(like);
            post.get().setQuantityOfLikes(likesSet.size());
            likeRepository.save(like);

        } else {
            likesSet = post.get().getPostLikes();
            likesSet.remove(likes.get());
            likeRepository.delete(likes.get());
            post.get().setQuantityOfLikes(likesSet.size());
        }

        var post1 = postsRepository.save(post.get());

        return new ResponseEntity<Posts>(post1, HttpStatus.CREATED);
    }

}
