package com.uee.backend.IT20122096.Points.Entity;

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
public class Point {
    @Id
    private ObjectId id;
    private ObjectId userId;
    private String year;
    private String month;
    private Integer points=0;
}
