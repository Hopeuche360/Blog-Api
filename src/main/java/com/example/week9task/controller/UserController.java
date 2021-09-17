package com.example.week9task.controller;

import com.example.week9task.dto.LoginDto;
import com.example.week9task.dto.SignUpDto;
import com.example.week9task.model.User;
import com.example.week9task.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> saveNewUser(@RequestBody SignUpDto signUpDto) {
        return (ResponseEntity<User>)userService.saveNewUser(signUpDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(userService.userLogin(loginDto), HttpStatus.OK);
    }

    @GetMapping("/get")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable(value = "userId") long userid) {
        return new ResponseEntity<>(userService.getUserById(userid), HttpStatus.OK);
    }

    @PutMapping("{userId}")
    public ResponseEntity<?> updateUser(@RequestBody User user,
                                           @PathVariable(value = "userId") long id) {
        return new ResponseEntity<>(userService.updateUser(user, id), HttpStatus.OK);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "userId") long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<String>("User successfully deleted", HttpStatus.OK);
    }
}
