package com.example.week9task.repository;

import com.example.week9task.model.UserConnections;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserConnectionRepository extends JpaRepository<UserConnections, Long> {
}
