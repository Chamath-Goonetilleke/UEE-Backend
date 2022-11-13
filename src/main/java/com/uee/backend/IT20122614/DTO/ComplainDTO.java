package com.uee.backend.IT20122614.DTO;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document
public class ComplainDTO {

    private ObjectId id;
    private String title;
    private String userName;
    private String imageUrl;
    private String content;
    private String createdDate;

}
