package com.uee.backend.IT20122096.Campaigns.Entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Document
public class Campaign {
    public enum Status{
        PENDING,HAPPENING,FINISH
    }
    @Id
    private ObjectId id;
    private ObjectId host;
    private String place;
    private String date;
    private String startTime;
    private String endTime;
    private String description;
    private String image;
    private Status status=Status.PENDING;

}
