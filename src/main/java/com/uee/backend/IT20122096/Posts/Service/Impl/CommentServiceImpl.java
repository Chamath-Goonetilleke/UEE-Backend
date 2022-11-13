package com.uee.backend.IT20122096.Posts.Service.Impl;

import com.uee.backend.IT20122096.LoginRegistrationAuth.Entity.User;
import com.uee.backend.IT20122096.LoginRegistrationAuth.Service.UserService;
import com.uee.backend.IT20122096.Points.Service.PointService;
import com.uee.backend.IT20122096.Posts.DTO.CommentDTO;
import com.uee.backend.IT20122096.Posts.DTO.CommentResponseDTO;
import com.uee.backend.IT20122096.Posts.Entity.Comment;
import com.uee.backend.IT20122096.Posts.Repository.CommentRepository;
import com.uee.backend.IT20122096.Posts.Service.CommentService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Component
public class CommentServiceImpl implements CommentService {

    final
    CommentRepository commentRepository;
    final
    UserService userService;
    final
    PointService pointService;

    public CommentServiceImpl(CommentRepository commentRepository, UserService userService, PointService pointService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.pointService = pointService;
    }

    @Override
    public Comment saveComment(CommentDTO commentDTO) {

        Comment comment = new Comment();
        comment.setPostId(commentDTO.getPostId());
        comment.setUserId(commentDTO.getUserId());
        comment.setComment(commentDTO.getComment());

        try {
            /**
             * award points for adding a comment*/
            pointService.updatePoint(commentDTO.getUserId(),5);
            return commentRepository.save(comment);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<CommentResponseDTO> getAllComments(ObjectId postId) {

        try {
            List<Comment> comments = commentRepository.getAllCommentsByPostId(postId);
            List<CommentResponseDTO> commentResponseList = new ArrayList<>();

            for (Comment comment: comments) {

                CommentResponseDTO commentResponse = new CommentResponseDTO();
                commentResponse.setId(comment.getId());
                commentResponse.setPostId(postId);

                ResponseEntity<?> userRes = userService.getUserById(comment.getUserId());
                User user = (User) userRes.getBody();
                user.setPassword(null);
                if (userRes.getStatusCodeValue()==HttpStatus.OK.value()){
                    commentResponse.setUser(user);
                }
                else {
                    commentResponse.setUser(null);
                }
                commentResponse.setComment(comment.getComment());

                commentResponseList.add(commentResponse);
            }

            return commentResponseList;
        }catch (Exception e){
            return Collections.emptyList();
        }
    }
}
