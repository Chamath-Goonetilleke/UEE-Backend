package com.uee.backend.IT20122614.Controller;

import com.uee.backend.IT20122614.DTO.Complain_CommentDTO;
import com.uee.backend.IT20122614.DTO.ComplainDTO;
import com.uee.backend.IT20122614.Service.ComplainService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/complain")
@CrossOrigin("*")
public class ComplainController {
    final ComplainService complainService;

    public ComplainController(ComplainService complainService) {
        this.complainService = complainService;
    }

    @PostMapping("/add-new")
    public ResponseEntity<?> saveComplain(@RequestBody ComplainDTO complainDTO){

        return complainService.saveComplain(complainDTO);
    }
    @GetMapping("/display-all-complains")
    public ResponseEntity<?> getAllComplains(){

        return complainService.getAllComplains();
    }
    @PostMapping("/add-comments")
    public ResponseEntity<?> addComments(@RequestBody Complain_CommentDTO complainCommentDTO){

        return complainService.addComments(complainCommentDTO);
    }
    @GetMapping("/get-comment/{id}")
    public ResponseEntity<?> getComments(@PathVariable String id){
        return complainService.getCommentsByID(id);
    }
}
