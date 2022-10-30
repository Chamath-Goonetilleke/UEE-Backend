package com.uee.backend.IT20122096;

import com.uee.backend.IT20122096.LoginRegistrationAuth.DTO.UserRegisterDTO;
import com.uee.backend.IT20122096.LoginRegistrationAuth.Entity.Admin;
import com.uee.backend.IT20122096.LoginRegistrationAuth.Entity.User;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class CommonData {


    public User getUser() {
        return new User(
                new ObjectId("6331d725d613b66fd9db0f19"),
                "Chamath",
                "admin@gmail.com",
                "$2a$10$4ZNog8tdUVODANq2pph7Lut/5svvn0kjy9DyfCJtJsfib6xsH3VDi",
                "gampaha",
                "0778528876",
                "ImageUrl");
    }

    public Admin getAdmin(UserRegisterDTO userRegisterDTO) {
        Admin user = new Admin(
                userRegisterDTO.getId(),
                userRegisterDTO.getName(),
                userRegisterDTO.getEmail(),
                userRegisterDTO.getPassword(),
                null,
                null,
                null,
                userRegisterDTO.getIsAdmin());


        return user;
    }

    public User getUserByDTOSave(UserRegisterDTO userRegisterDTO) {
        User user = new User(
                userRegisterDTO.getId(), userRegisterDTO.getName(), userRegisterDTO.getEmail(), userRegisterDTO.getPassword(), null, null, null);
        return user;
    }

    public List<User> getUserList() {
        List<User> userList = new ArrayList<>();
        for (int x = 0; x < 4; x++) {
            User user = new User(
                    new ObjectId("6" + x + "31d725d613b66fd9db0f19"),
                    "Chamath",
                    "admin@gmail.com",
                    "123456789",
                    "gampaha",
                    "0778528876",
                    "ImageUrl");
            userList.add(user);
        }
        return userList;
    }



}
