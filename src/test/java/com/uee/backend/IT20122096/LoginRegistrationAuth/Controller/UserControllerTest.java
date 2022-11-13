package com.uee.backend.IT20122096.LoginRegistrationAuth.Controller;


import com.uee.backend.IT20122096.LoginRegistrationAuth.Entity.JWTRequest;
import com.uee.backend.IT20122096.LoginRegistrationAuth.Entity.JWTResponse;
import com.uee.backend.IT20122096.LoginRegistrationAuth.Repository.UserRepository;
import com.uee.backend.IT20122096.LoginRegistrationAuth.Service.UserServiceImpl;
import com.uee.backend.IT20122096.LoginRegistrationAuth.Utility.JWTUtility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    UserController userController;
    @MockBean
    UserRepository userRepository;
    @MockBean
    JWTUtility jwtUtility;
    @MockBean
    AuthenticationManager authenticationManager;
    @MockBean
    UserServiceImpl userServiceImpl;

    @Test
    void TestAuthenticateSuccess() throws Exception {

        Authentication authentication = mock(Authentication.class);
        authentication.setAuthenticated(true);

        when(authentication.isAuthenticated()).thenReturn(true);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(jwtUtility.generateToken(new User("admin@gmail.com","123456789",new ArrayList<>()))).thenReturn("token124");
        when(userServiceImpl.loadUserByUsername("admin@gmail.com")).thenReturn(new User("admin@gmail.com","123456789",new ArrayList<>()));

       ResponseEntity<?> result = userController.authenticate(new JWTRequest("admin@gmail.com","123456789"));
        Assertions.assertEquals("token124",((JWTResponse) Objects.requireNonNull(result.getBody())).getJwtToken());
    }
    @Test
    void TestGetAllUsers(){
        Mockito.when(userServiceImpl.getAllUsers()).thenReturn(new ResponseEntity("TestUsers", HttpStatus.OK));
        ResponseEntity<?> result = userController.getAllUsers();
        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
        Assertions.assertNotNull(result.getBody());
    }
    @Test
    void TestGetUserById(){
        Mockito.when(userServiceImpl.getUserById(any())).thenReturn(new ResponseEntity("TestUser", HttpStatus.OK));
        ResponseEntity<?> result = userController.getUserById(any());
        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
        Assertions.assertNotNull(result.getBody());
    }
    @Test
    void TestSaveUser(){
        Mockito.when(userServiceImpl.saveUser(any())).thenReturn(new ResponseEntity("TestUser", HttpStatus.OK));
        ResponseEntity<?> result = userController.saveUser(any());
        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
        Assertions.assertNotNull(result.getBody());
    }
    @Test
    void TestUpdateUser(){
        Mockito.when(userServiceImpl.updateUser(any())).thenReturn(new ResponseEntity("TestUser", HttpStatus.OK));
        ResponseEntity<?> result = userController.updateUser(any());
        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
        Assertions.assertNotNull(result.getBody());
    }



}
