package com.uee.backend.IT20122096.Posts.Entity;

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
public class Comment {
    @Id
    private ObjectId id;
    private ObjectId userId;
    private ObjectId postId;
    private String comment;
}
