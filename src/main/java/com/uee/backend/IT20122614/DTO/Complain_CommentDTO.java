package com.uee.backend.IT20122614.DTO;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Complain_CommentDTO {
    private String complainId;
    private String userName;
    private String comment;
}
