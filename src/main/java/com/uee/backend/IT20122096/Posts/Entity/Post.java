package com.uee.backend.IT20122096.Posts.Entity;

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
public class Post {
    @Id
    private ObjectId id;
    private ObjectId userId;
    private ObjectId campaignId;
    private String description;
    private String image;


}
