package com.uee.backend.IT20122614.Model;

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
public class Blog {
    @Id
    private ObjectId id;
    private String title;
    private String userName;
    private String imageUrl;
    private String content;
    private String time;
}
