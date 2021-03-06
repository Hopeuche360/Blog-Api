package com.example.week9task.service.serviceImpl;

import com.example.week9task.dto.LoginDto;
import com.example.week9task.dto.SignUpDto;
import com.example.week9task.dto.ApiResponse;
import com.example.week9task.exception.AppException;
import com.example.week9task.exception.ResourceNotFoundException;
import com.example.week9task.model.User;
import com.example.week9task.repository.UserRepository;
import com.example.week9task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public ResponseEntity<?> saveNewUser(SignUpDto signUpDto) throws AppException {
        ApiResponse userResponse = new ApiResponse();
        User user = new User();
        Optional<User> dbUser = userRepository.findUserByUserNameAndPassword(signUpDto.getUserName(), signUpDto.getPassword());
        if (dbUser.isEmpty()) {
            user.setUserName(signUpDto.getUserName());
            user.setName(signUpDto.getName());
            user.setEmail(signUpDto.getEmail());
            user.setPassword(signUpDto.getPassword());
            user.setPhoneNumber(signUpDto.getPhoneNumber());
            User newUser = userRepository.save(user);
            userResponse.setData(newUser);
            userResponse.setMessage("User successfully registered");
            return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
        }
        throw new AppException("User already exist. A unique username is required", HttpStatus.BAD_REQUEST);
    }


    @Override
    public ResponseEntity<?> userLogin(LoginDto loginDto, HttpSession httpSession) {
        ApiResponse apiResponse = new ApiResponse();
        Optional<User> user = userRepository.findUserByUserNameAndPassword(loginDto.getUserName(), loginDto.getPassword());
        if (user.isPresent()){
            User loggedInUser = (User) httpSession.getAttribute(user.get().getUserName());
            if (loggedInUser !=null) {
                apiResponse.setMessage("Welcome Back");
                apiResponse.setData(user);
                return new ResponseEntity<>(apiResponse, HttpStatus.OK);
            }
            httpSession.setAttribute(user.get().getUserName(), user);
            apiResponse.setMessage("Login Successful");
            apiResponse.setData(user);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        throw new ResourceNotFoundException("user", "userId", loginDto);
    }

    @Override
    public ResponseEntity<?> userLogout(long userId, HttpSession httpSession) {
        ApiResponse response = new ApiResponse();
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()){
            User loggedInUser = (User) httpSession.getAttribute(user.get().getUserName());
            if (loggedInUser !=null) {
                httpSession.removeAttribute(user.get().getUserName());
                response.setMessage("Log out successful");
                response.setData(user);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        throw new ResourceNotFoundException("user", "userId", userId);
    }


    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }


    @Override
    public User getUserById(long userId) {
        Optional<User> dbUser = userRepository.findById(userId);
        if (dbUser.isPresent()){
            return dbUser.get();
        } else {
            throw new ResourceNotFoundException("User", "userId", userId);
        }
    }


    @Override
    public User updateUser(User user, long id) {
        User existingUser = userRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("User", "id", id));
        existingUser.setName(user.getName());
        existingUser.setUserName(user.getUserName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setPassword(user.getPassword());
        userRepository.save(existingUser);
        return existingUser;
    }


    @Override
    public void deleteUserById(long id) {
        var user =userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", "id", id));
        user.setFlag(true);
        userRepository.save(user);
    }

    @Override
    public void cancelDelete(long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->
                new ResourceNotFoundException("User", "userId", userId));
        if (user.isFlag()) {
            user.setFlag(false);
            userRepository.save(user);
        }
    }

}
