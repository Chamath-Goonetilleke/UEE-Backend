package com.uee.backend.IT20122096.LoginRegistrationAuth.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtTokenDTO {

    private String id;
    private boolean isAdmin=false;
}
