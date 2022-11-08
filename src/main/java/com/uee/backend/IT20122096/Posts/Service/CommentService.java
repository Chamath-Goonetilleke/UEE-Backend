package com.uee.backend.IT20122096.Posts.Service;

import com.uee.backend.IT20122096.Posts.DTO.CommentDTO;
import com.uee.backend.IT20122096.Posts.DTO.CommentResponseDTO;
import com.uee.backend.IT20122096.Posts.Entity.Comment;
import org.bson.types.ObjectId;

import java.util.List;

public interface CommentService {

    Comment saveComment(CommentDTO commentDTO);
    List<CommentResponseDTO> getAllComments(ObjectId postId);

}
