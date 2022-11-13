package com.uee.backend.IT20122096.Posts.DTO;

import com.uee.backend.IT20122096.Campaigns.DTO.ContributionResponseDTO;
import com.uee.backend.IT20122096.Campaigns.Entity.Campaign;
import lombok.*;
import org.bson.types.ObjectId;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDTO {
    private ObjectId id;
    private ObjectId userId;
    private Campaign campaign;
    private String description;
    private String image;
    private Integer likeCount;
    private Integer commentsCount;
    private List<CommentResponseDTO> comments;
    private List<ContributionResponseDTO> contributors;


}
