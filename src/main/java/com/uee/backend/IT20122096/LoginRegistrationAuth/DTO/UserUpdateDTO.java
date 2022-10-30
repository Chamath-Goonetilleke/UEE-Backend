package com.uee.backend.IT20122096.LoginRegistrationAuth.DTO;

import lombok.*;
import org.bson.types.ObjectId;


import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {

    private ObjectId id;
    @Size(min = 3, message = "must be longer than 3 characters")
    private String name;
    @Size(max = 10, min = 5, message = "must in range 5-10")
    private String password;
    private String currentPassword;
    private String address;
    @Pattern(regexp = "(0)\\d{9}", message = "Must be a valid phone number")
    private String phoneNumber;
    private String image;
}
