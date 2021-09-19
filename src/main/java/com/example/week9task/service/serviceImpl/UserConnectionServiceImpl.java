package com.example.week9task.service.serviceImpl;

import com.example.week9task.exception.ResourceNotFoundException;
import com.example.week9task.model.Posts;
import com.example.week9task.model.User;
import com.example.week9task.model.UserConnections;
import com.example.week9task.repository.UserConnectionRepository;
import com.example.week9task.repository.UserRepository;
import com.example.week9task.service.UserConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserConnectionServiceImpl implements UserConnectionService {

    private final UserRepository userRepository;
    private final UserConnectionRepository userConnectionRepository;

    @Autowired
    public UserConnectionServiceImpl(UserRepository userRepository, UserConnectionRepository userConnectionRepository) {
        this.userRepository = userRepository;
        this.userConnectionRepository = userConnectionRepository;
    }

    @Override
    public ResponseEntity<?> createNewConnection(long userId1, long userId2) {
        User user1 = userRepository.findById(userId1).orElseThrow(()->
                new ResourceNotFoundException("User", "UserId1", userId1));
        User user2 = userRepository.findById(userId2).orElseThrow(()->
                new ResourceNotFoundException("User", "userId2", userId2));

        UserConnections userConnections =new UserConnections();
        userConnections.setUser(user2);
        userConnectionRepository.save(userConnections);
        user1.getFriendList().add(userConnections);

        UserConnections userConnection = new UserConnections();
        userConnection.setUser(user1);
        userConnectionRepository.save(userConnection);
        user2.getFriendList().add(userConnection);




//        if (user1.getUserConnections() == null) {
//            userConnections = new UserConnections();
//            userConnections.getConnectionList().add(user2);
////            user1.setUserConnections(userConnections);
//            userConnectionRepository.save(userConnections);
//        } else {
//            userConnections = user1.getUserConnections();
//            userConnections.getConnectionList().add(user2);
//            user1.setUserConnections(userConnections);
//        }
//        if (user2.getUserConnections() != null) {
//            userConnections = user2.getUserConnections();
//            userConnections.getConnectionList().add(user1);
//            user2.setUserConnections(userConnections);
//        } else {
//            userConnections = new UserConnections();
//            userConnections.getConnectionList().add(user1);
//            user2.setUserConnections(userConnections);
//            userConnectionRepository.save(userConnections);
//        }
        userRepository.save(user1);
        userRepository.save(user2);
        return new ResponseEntity<>("User Connection created", HttpStatus.CREATED);
    }

    @Override
    public Set<User> getListOfUserConnection(long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->
                new ResourceNotFoundException("User", "userId", userId));
        Set<UserConnections> friendList = user.getFriendList();
        Set<User> users = new HashSet<>();
        for(UserConnections con : friendList){
            users.add(con.getUser());
        }
        return users;
    }
}
