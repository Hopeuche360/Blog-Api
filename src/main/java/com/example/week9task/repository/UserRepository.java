package com.example.week9task.repository;

import com.example.week9task.model.Posts;
import com.example.week9task.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserByUserNameAndPassword(String userName, String password);
    List<User> findUserByFlag(boolean flag);
}
