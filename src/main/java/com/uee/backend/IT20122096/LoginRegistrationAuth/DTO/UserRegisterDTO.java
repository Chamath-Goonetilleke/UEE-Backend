package com.uee.backend.IT20122096.LoginRegistrationAuth.DTO;

import lombok.*;
import org.bson.types.ObjectId;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO {

    private ObjectId id;
    @NotNull(message = "Name is Mandatory")
    @Size(min = 3, message = "must be longer than 3 characters")
    private String name;
    @NotNull(message = "Email is Mandatory")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",message = "Must be a valid email")
    private String email;
    @NotNull(message = "Password is Mandatory")
    @Size(max = 10, min = 5, message = "must in range 5-10")
    private String password;

    private Boolean isAdmin=false;
}
