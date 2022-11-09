package com.uee.backend.IT20122096.Points.DTO;

import com.uee.backend.IT20122096.LoginRegistrationAuth.Entity.User;
import lombok.*;
import org.bson.types.ObjectId;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PointDTO {
    private ObjectId id;
    private User user;
    private String year;
    private String month;
    private Integer points;
}
