package com.example.week9task.service;

import com.example.week9task.dto.LoginDto;
import com.example.week9task.dto.SignUpDto;
import com.example.week9task.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public interface UserService {
    ResponseEntity<?> saveNewUser(SignUpDto signUpDto);
    ResponseEntity<?> userLogin(LoginDto loginDto, HttpSession httpSession);
    ResponseEntity<?> userLogout(long userId, HttpSession httpSession);
    List<User> getAllUsers();
    User getUserById(long userId);
    User updateUser(User user, long id);
    void deleteUserById(long id);
    void cancelDelete(long userId);
}
