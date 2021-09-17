package com.example.week9task.repository;

import com.example.week9task.model.PostLikes;
import com.example.week9task.model.Posts;
import com.example.week9task.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLikes, Long> {
    Optional<PostLikes> findPostLikesByUserAndPosts(User user, Posts posts);
}
