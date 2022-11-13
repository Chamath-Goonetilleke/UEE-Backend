package com.uee.backend.IT20122614.Service;

import com.uee.backend.IT20122614.DTO.Complain_CommentDTO;
import com.uee.backend.IT20122614.DTO.ComplainDTO;
import com.uee.backend.IT20122614.Model.Complain_Comment;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface ComplainService {

    ResponseEntity<?> saveComplain(ComplainDTO complainDTO);
    ResponseEntity<?> addComments(Complain_CommentDTO complainCommentDTO);
    ResponseEntity<?> getAllComplains();

    ResponseEntity<?> getCommentsByID(String id);
}
