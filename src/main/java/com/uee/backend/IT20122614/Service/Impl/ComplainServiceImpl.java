package com.uee.backend.IT20122614.Service.Impl;

import com.uee.backend.IT20122614.DTO.Complain_CommentDTO;
import com.uee.backend.IT20122614.DTO.ComplainDTO;
import com.uee.backend.IT20122614.Model.Complain_Comment;
import com.uee.backend.IT20122614.Model.Complain;
import com.uee.backend.IT20122614.Repository.CommentRepository;
import com.uee.backend.IT20122614.Repository.ComplainRepository;
import com.uee.backend.IT20122614.Service.ComplainService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Component
public class ComplainServiceImpl implements ComplainService {
    final ComplainRepository complainRepository;
    final CommentRepository commentRepository;

    public ComplainServiceImpl(ComplainRepository complainRepository, CommentRepository commentRepository) {
        this.complainRepository = complainRepository;
        this.commentRepository = commentRepository;
    }


    @Override
    public ResponseEntity<?> saveComplain(ComplainDTO complainDTO) {

        try{
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime now = LocalDateTime.now();
            System.out.println(dtf.format(now));

            Complain complainSave = new Complain();
            complainSave.setTitle(complainDTO.getTitle());
            complainSave.setUserName(complainDTO.getUserName());
            complainSave.setImageUrl(complainDTO.getImageUrl());
            complainSave.setContent(complainDTO.getContent());
            complainSave.setCreatedDate(dtf.format(now));

            complainRepository.save(complainSave);

            return ResponseEntity.status(HttpStatus.OK).body("Successfully created");


        }catch(Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot create new Complain");
        }

    }

    @Override
    public ResponseEntity<?> addComments(Complain_CommentDTO complainCommentDTO) {
        try{
            Complain_Comment complainComment = new Complain_Comment();
            complainComment.setComplainId(complainCommentDTO.getComplainId());
            complainComment.setUserName(complainCommentDTO.getUserName());
            complainComment.setComment(complainCommentDTO.getComment());

            commentRepository.save(complainComment);
            return ResponseEntity.status(HttpStatus.OK).body(complainComment);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot create new Complain");
        }
    }


    @Override
    public ResponseEntity<?> getAllComplains() {
        try{
            List<Complain> complains = complainRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(complains);

        }catch(Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot create new Complain");
        }
    }

    @Override
    public ResponseEntity<?> getCommentsByID(String id) {



        try{
            List<Complain_Comment> comment = commentRepository.findByComplainId(id);
            return ResponseEntity.status(HttpStatus.OK).body(comment);

        }catch(Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot create new comment");
        }

    }
}
