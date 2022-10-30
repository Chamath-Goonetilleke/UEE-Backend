package com.uee.backend.IT20122096.Campaigns.Entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Document
public class Contribution {
    public enum Status{
        ABSENT,PRESENT
    }
    @Id
    private ObjectId id;
    private ObjectId user;
    private ObjectId campaign;
    private String firstName;
    private String lastName;
    private String contactNo;
    private String contribution;
    private Status attendance=Status.ABSENT;
}
