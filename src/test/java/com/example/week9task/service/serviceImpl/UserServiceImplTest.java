package com.example.week9task.service.serviceImpl;

import com.example.week9task.dto.SignUpDto;
import com.example.week9task.exception.ResourceNotFoundException;
import com.example.week9task.model.User;
import com.example.week9task.repository.UserRepository;
import com.example.week9task.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    void shouldSaveUserSuccessfully() {

        User user = new User();
        SignUpDto signUpDto = new SignUpDto();
        signUpDto.setName("Ada");
        signUpDto.setUserName("Bekke");
        signUpDto.setEmail("ada@gmail.com");
        signUpDto.setPhoneNumber("08131200463");
        signUpDto.setPassword("123456");

        user.setName(signUpDto.getName());
        user.setUserName(signUpDto.getUserName());
        user.setEmail(signUpDto.getEmail());
        user.setPhoneNumber(signUpDto.getPhoneNumber());
        user.setPassword(signUpDto.getPassword());


        given(userRepository.save(user)).willReturn(user);
        when(userRepository.save(user)).then(invocation -> invocation.getArgument(0));
//        User user1 = userService.saveNewUser(user);

//        assert(user1).equals(user);
//        verify(userRepository).save(any(user.class));


//        givenUserIdNotFound_whenGetUserByIdIsCalled_thenNotFoundExceptionIsThrown()

    }

    @Test
    void userLogin() {
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void getUserById() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUserById() {
    }
}