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
public class Complain_Comment {

    @Id
    private ObjectId id;
    private String complainId;
    private String userName;
    private String comment;
}
