package com.example.week9task.controller;

import com.example.week9task.service.UserConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/connections")
public class UserConnectionController {
    private final UserConnectionService userConnectionService;

    @Autowired
    public UserConnectionController(UserConnectionService userConnectionService) {
        this.userConnectionService = userConnectionService;
    }

    @PostMapping("/new-connection/{user1Id}/{user2Id}")
    public ResponseEntity<?> establishConnectionBetweenUsers(@PathVariable(value = "user1Id") long userId1,
                                                             @PathVariable(value = "user2Id") long userId2) {
        return new ResponseEntity<>(userConnectionService.createNewConnection(userId1, userId2), HttpStatus.OK);
    }

    @GetMapping("/get-connections/{userid}")
    public ResponseEntity<?> getAllUserConnections(@PathVariable(value = "userid") long userId) {
        return new ResponseEntity<>(userConnectionService.getListOfUserConnection(userId), HttpStatus.OK);
    }
}