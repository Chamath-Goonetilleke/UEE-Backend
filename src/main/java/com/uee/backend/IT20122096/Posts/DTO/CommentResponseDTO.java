package com.uee.backend.IT20122096.Posts.DTO;

import com.uee.backend.IT20122096.LoginRegistrationAuth.Entity.User;
import lombok.*;
import org.bson.types.ObjectId;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDTO {
    private ObjectId id;
    private ObjectId postId;
    private User user;
    private String comment;
}
