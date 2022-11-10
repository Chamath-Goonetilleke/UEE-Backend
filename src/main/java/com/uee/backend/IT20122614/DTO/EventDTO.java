package com.uee.backend.IT20122614.DTO;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document
public class EventDTO {

    private ObjectId id;
    private String title;
    private String userName;
    private String imageUrl;
    private String content;
    private String time;
    private String date;
    private String createdDate;
}
