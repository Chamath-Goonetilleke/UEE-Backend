package com.uee.backend.IT20122096.LoginRegistrationAuth.Controller;


import com.uee.backend.IT20122096.LoginRegistrationAuth.DTO.UserRegisterDTO;
import com.uee.backend.IT20122096.LoginRegistrationAuth.DTO.UserUpdateDTO;
import com.uee.backend.IT20122096.LoginRegistrationAuth.Entity.JWTRequest;
import com.uee.backend.IT20122096.LoginRegistrationAuth.Entity.JWTResponse;
import com.uee.backend.IT20122096.LoginRegistrationAuth.Service.UserService;
import com.uee.backend.IT20122096.LoginRegistrationAuth.Service.UserServiceImpl;
import com.uee.backend.IT20122096.LoginRegistrationAuth.Utility.JWTUtility;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")

public class UserController  {
    private final JWTUtility jwtUtility;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public UserController(JWTUtility jwtUtility, AuthenticationManager authenticationManager, UserServiceImpl userService) {
        this.jwtUtility = jwtUtility;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/auth")
    public ResponseEntity<?> authenticate(@RequestBody JWTRequest jwtRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword())
            );
        } catch (BadCredentialsException exception) {
            return new ResponseEntity<>("Invalid Credentials", HttpStatus.BAD_REQUEST);
        }
        final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());
        final String token = jwtUtility.generateToken(userDetails);

        return new ResponseEntity<>(new JWTResponse(token), HttpStatus.OK);
    }
    @GetMapping("/user/getAll")
    public ResponseEntity<?> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable ObjectId id) {
        return userService.getUserById(id);
    }
    @PostMapping("/user/save")
    public ResponseEntity<?> saveUser(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
       return userService.saveUser(userRegisterDTO);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/user/update")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserUpdateDTO userUpdateDTO){
        return userService.updateUser(userUpdateDTO);
    }



}