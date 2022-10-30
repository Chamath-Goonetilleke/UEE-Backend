package com.uee.backend.IT20122096.LoginRegistrationAuth.Entity;


import lombok.*;
import org.bson.types.ObjectId;

@Getter
@Setter
@ToString
@NoArgsConstructor

public class Admin extends User {
    private Boolean isAdmin=false;

    public Admin(ObjectId id, String name, String email, String password, String address, String phoneNumber, String image, Boolean isAdmin) {
        super(id, name, email, password, address, phoneNumber, image);
        this.isAdmin = isAdmin;
    }


}
