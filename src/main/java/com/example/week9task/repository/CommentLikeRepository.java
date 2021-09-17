package com.example.week9task.repository;

import com.example.week9task.model.CommentLikes;
import com.example.week9task.model.Comments;
import com.example.week9task.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLikes, Long> {
    Optional<CommentLikes> findCommentLikesByUserAndComments(User user, Comments comments);
}
