package com.uee.backend.IT20122096.Posts.Controller;

import com.uee.backend.IT20122096.Posts.DTO.CommentDTO;
import com.uee.backend.IT20122096.Posts.DTO.PostDTO;
import com.uee.backend.IT20122096.Posts.DTO.PostResponseDTO;
import com.uee.backend.IT20122096.Posts.Entity.Comment;
import com.uee.backend.IT20122096.Posts.Entity.Post;
import com.uee.backend.IT20122096.Posts.Service.CommentService;
import com.uee.backend.IT20122096.Posts.Service.PostService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    final
    PostService postService;
    final
    CommentService commentService;

    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> savePost(@RequestBody PostDTO postDTO) {
        Post post = postService.savePost(postDTO);
        if (post != null) {
            return new ResponseEntity<>("Saved Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.BAD_REQUEST);

        }

    }

    @GetMapping("/getAllPosts")
    public ResponseEntity<?> getAllPosts() {
        List<PostResponseDTO> posts = postService.getAllPosts();
        if (posts==null) {
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(posts, HttpStatus.OK);
        }
    }

    @GetMapping("/getAllPostsByUserId/{userId}")
    public ResponseEntity<?> getAllPostsByUserId(@PathVariable ObjectId userId) {
        List<PostResponseDTO> posts = postService.getAllPostsByUserId(userId);
        if (posts==null) {
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(posts, HttpStatus.OK);
        }
    }

    @GetMapping("/getPostsById/{postId}")
    public ResponseEntity<?> getPostsById(@PathVariable ObjectId postId) {
        PostResponseDTO post = postService.getPostsById(postId);
        if (post==null) {
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(post, HttpStatus.OK);
        }
    }

    @DeleteMapping("/deletePost/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable ObjectId postId){
        boolean status = postService.deletePost(postId);
        if (status){
            return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);

        }else {
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/addComment")
    public ResponseEntity<?> saveComment(@RequestBody CommentDTO commentDTO){
        Comment comment = commentService.saveComment(commentDTO);
        if (comment != null) {
            return new ResponseEntity<>("Saved Successfully", HttpStatus.OK);
        }else {
             return new ResponseEntity<>("Something Went Wrong", HttpStatus.BAD_REQUEST);
        }
    }

}
