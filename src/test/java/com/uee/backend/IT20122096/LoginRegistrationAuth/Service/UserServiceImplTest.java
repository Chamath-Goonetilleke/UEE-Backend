package com.uee.backend.IT20122096.LoginRegistrationAuth.Service;

import com.uee.backend.IT20122096.CommonData;
import com.uee.backend.IT20122096.LoginRegistrationAuth.DTO.UserRegisterDTO;
import com.uee.backend.IT20122096.LoginRegistrationAuth.DTO.UserUpdateDTO;
import com.uee.backend.IT20122096.LoginRegistrationAuth.Entity.Admin;
import com.uee.backend.IT20122096.LoginRegistrationAuth.Entity.User;
import com.uee.backend.IT20122096.LoginRegistrationAuth.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceImplTest {

    @Autowired
    UserServiceImpl userServiceImpl;
    @MockBean
    UserRepository userRepository;

    CommonData commonData = new CommonData();

    @Test
    void TestLoadUserByUsername() {
        Mockito.when(userRepository.findUserByEmail("admin@gmail.com")).thenReturn(commonData.getUser());

        UserDetails result = userServiceImpl.loadUserByUsername("admin@gmail.com");
        Assertions.assertEquals("admin@gmail.com", result.getUsername());

    }

    @Test
    void TestGetAllUsers() {
        Mockito.when(userRepository.findAll()).thenReturn(commonData.getUserList());

        ResponseEntity<List<User>> result = userServiceImpl.getAllUsers();
        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
        Assertions.assertEquals(4, Objects.requireNonNull(result.getBody()).size());
    }

    @Test
    void TestGetUserByIdSuccess() {
        Mockito.when(userRepository.findById(new ObjectId("6331d725d613b66fd9db0f19"))).thenReturn(Optional.of(commonData.getUser()));

        ResponseEntity<?> result = userServiceImpl.getUserById(new ObjectId("6331d725d613b66fd9db0f19"));
        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
        Assertions.assertEquals("Chamath", ((User) Objects.requireNonNull(result.getBody())).getName());
    }

    @Test
    void TestGetUserByIdFailure() {
        Mockito.when(userRepository.findById(new ObjectId("6331d725d613b66fd9db0f19"))).thenReturn(Optional.empty());

        ResponseEntity<?> result = userServiceImpl.getUserById(new ObjectId("6331d725d613b66fd9db0f19"));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), result.getStatusCodeValue());
        Assertions.assertEquals("User dose not exist", result.getBody());
    }

    @Test
    void TestGetUserByEmail() {
        Mockito.when(userRepository.findUserByEmail("admin@gmail.com")).thenReturn(commonData.getUser());

        String result = userServiceImpl.getUserByEmail("admin@gmail.com");
        Assertions.assertEquals("6331d725d613b66fd9db0f19", result);

    }

    @Test
    void TestSaveUserAdmin() {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setName("Chamath");
        userRegisterDTO.setEmail("admin@gmail.com");
        userRegisterDTO.setPassword("123456789");
        userRegisterDTO.setIsAdmin(true);

        Mockito.when(userRepository.findUserByEmail("admin@gmail.com")).thenReturn(null);
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(commonData.getAdmin(userRegisterDTO));

        ResponseEntity<?> result = userServiceImpl.saveUser(userRegisterDTO);
        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
        Assertions.assertNotNull(result.getBody());
        Assertions.assertEquals(true, ((Admin)result.getBody()).getIsAdmin());

    }
    @Test
    void TestSaveUserVisitor() {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setName("Chamath");
        userRegisterDTO.setEmail("admin@gmail.com");
        userRegisterDTO.setPassword("123456789");
        userRegisterDTO.setIsAdmin(false);

        Mockito.when(userRepository.findUserByEmail("admin@gmail.com")).thenReturn(null);
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(commonData.getUserByDTOSave(userRegisterDTO));

        ResponseEntity<?> result = userServiceImpl.saveUser(userRegisterDTO);
        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
        Assertions.assertNotNull(result.getBody());
    }
    @Test
    void TestSaveUserFailure() {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setName("Chamath");
        userRegisterDTO.setEmail("admin@gmail.com");
        userRegisterDTO.setPassword("123456789");
        userRegisterDTO.setIsAdmin(false);

        Mockito.when(userRepository.findUserByEmail("admin@gmail.com")).thenReturn(commonData.getUser());
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(commonData.getAdmin(userRegisterDTO));

        ResponseEntity<?> result = userServiceImpl.saveUser(userRegisterDTO);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), result.getStatusCodeValue());
        Assertions.assertEquals("User Already Exist",result.getBody());
    }

    @Test
    void TestUpdateUserSuccess() {

        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        userUpdateDTO.setId(new ObjectId("6331d725d613b66fd9db0f19"));
        userUpdateDTO.setName("Chamath");
        userUpdateDTO.setPassword("123456789");
        userUpdateDTO.setCurrentPassword("123456789");
        userUpdateDTO.setAddress("Gamapaha");
        userUpdateDTO.setPhoneNumber("0778528876");
        userUpdateDTO.setImage("imageUrl");


        Mockito.when(userRepository.findById(new ObjectId("6331d725d613b66fd9db0f19"))).thenReturn(Optional.of(commonData.getUser()));
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(commonData.getUser());

        ResponseEntity<?> result = userServiceImpl.updateUser(userUpdateDTO);
        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
        Assertions.assertNotNull(result.getBody());


    }
    @Test
    void TestUpdateUserWrongPassword() {

        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        userUpdateDTO.setId(new ObjectId("6331d725d613b66fd9db0f19"));
        userUpdateDTO.setName("Chamath");
        userUpdateDTO.setPassword("123456789");
        userUpdateDTO.setCurrentPassword("123456781");
        userUpdateDTO.setAddress("Gamapaha");
        userUpdateDTO.setPhoneNumber("0778528876");
        userUpdateDTO.setImage("imageUrl");


        Mockito.when(userRepository.findById(new ObjectId("6331d725d613b66fd9db0f19"))).thenReturn(Optional.of(commonData.getUser()));
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(commonData.getUser());

        ResponseEntity<?> result = userServiceImpl.updateUser(userUpdateDTO);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), result.getStatusCodeValue());
        Assertions.assertEquals("Wrong Password",result.getBody());


    }
    @Test
    void TestUpdateUserFailure() {

        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        userUpdateDTO.setId(new ObjectId("6331d725d613b66fd9db0f19"));
        userUpdateDTO.setName("Chamath");
        userUpdateDTO.setPassword("123456789");
        userUpdateDTO.setCurrentPassword("123456781");
        userUpdateDTO.setAddress("Gamapaha");
        userUpdateDTO.setPhoneNumber("0778528876");
        userUpdateDTO.setImage("imageUrl");


        Mockito.when(userRepository.findById(new ObjectId("6331d725d613b66fd9db0f19"))).thenReturn(Optional.empty());
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(commonData.getUser());

        ResponseEntity<?> result = userServiceImpl.updateUser(userUpdateDTO);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), result.getStatusCodeValue());
        Assertions.assertEquals("User dose not exist",result.getBody());


    }

}
