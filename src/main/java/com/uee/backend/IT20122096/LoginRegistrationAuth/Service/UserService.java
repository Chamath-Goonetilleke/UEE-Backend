package com.uee.backend.IT20122096.LoginRegistrationAuth.Service;


import com.uee.backend.IT20122096.LoginRegistrationAuth.DTO.UserRegisterDTO;
import com.uee.backend.IT20122096.LoginRegistrationAuth.DTO.UserUpdateDTO;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService  {
    UserDetails loadUserByUsername(String email);
    ResponseEntity<?> getAllUsers();
    ResponseEntity<?> getUserById(ObjectId id);
    ResponseEntity<?> saveUser(UserRegisterDTO userDTO);
    ResponseEntity<?> updateUser(UserUpdateDTO userDTO);
    String getUserByEmail(String email);




}
