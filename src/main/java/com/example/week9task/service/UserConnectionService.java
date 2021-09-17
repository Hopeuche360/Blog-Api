package com.example.week9task.service;

import com.example.week9task.model.User;
import com.example.week9task.model.UserConnections;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface UserConnectionService {
    UserConnections createNewConnection(long userId1, long userId2);
    Set<User> getListOfUserConnection(long userId);
}
