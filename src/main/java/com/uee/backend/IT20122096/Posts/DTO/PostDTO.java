package com.uee.backend.IT20122096.Posts.DTO;

import lombok.*;
import org.bson.types.ObjectId;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private ObjectId id;
    private ObjectId userId;
    private ObjectId campaignId;
    private String description;
    private String image;
}
