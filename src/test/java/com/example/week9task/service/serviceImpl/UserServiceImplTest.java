package com.example.week9task.service.serviceImpl;

import com.example.week9task.dto.ApiResponse;
import com.example.week9task.dto.SignUpDto;
import com.example.week9task.exception.ResourceNotFoundException;
import com.example.week9task.model.User;
import com.example.week9task.repository.UserRepository;
import com.example.week9task.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        User user = new User();
        SignUpDto signUpDto = new SignUpDto();

        signUpDto.setName("Ada");
        signUpDto.setUserName("Bekkel");
        signUpDto.setEmail("ada@gmail.com");
        signUpDto.setPhoneNumber("08131200463");
        signUpDto.setPassword("123456");

        user.setName(signUpDto.getName());
        user.setUserName(signUpDto.getUserName());
        user.setEmail(signUpDto.getEmail());
        user.setPhoneNumber(signUpDto.getPhoneNumber());
        user.setPassword(signUpDto.getPassword());
    }
    @Test
    void shouldSaveUserSuccessfully() {

        SignUpDto signUpDto = new SignUpDto();

        ResponseEntity<?> responseEntity = userService.saveNewUser(signUpDto);

        assert responseEntity.getStatusCode().equals(HttpStatus.CREATED);

    }

//    @Test
//    void userLogin() {

//    }

//    @Test
//    void getAllUsers() {
//        User user = new User();
//        List<User> users = new ArrayList<>();
//        users.add(user);
//
//        given(userRepository.findAll()).willReturn(users);
//        User expected = (User) userService.getAllUsers();
//        assertEquals(expected, user);
//    }

    @Test
    void getUserById() {
        Long id = 1L;
        User user = new User();
        given(userRepository.findById(id)).willReturn(Optional.of(user));
        assertNotNull(userService.getUserById(id));
    }

    @Test
    void updateUser() {
        User user = new User();
        Long id = 1L;
//        assertNotNull(userService.updateUser(user, id));
        assertThrows(ResourceNotFoundException.class, ()-> {userService.updateUser(user, id);
        });
    }

//    @Test
//    void deleteUserById() {
//        User user = new User();
//        Long id = 1L;
//        user.setFlag(true);
//        assertEquals(userService.deleteUserById(id), true);
//    }
}