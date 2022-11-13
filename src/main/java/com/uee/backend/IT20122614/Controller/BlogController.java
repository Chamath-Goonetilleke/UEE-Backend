package com.uee.backend.IT20122614.Controller;

import com.uee.backend.IT20122614.DTO.BlogDTO;
import com.uee.backend.IT20122614.Model.Blog;
import com.uee.backend.IT20122614.Service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog")
@CrossOrigin("*")
public class BlogController {
    final
    BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }
    @PostMapping("/add-new")
    public ResponseEntity<?> saveBlog(@RequestBody BlogDTO blogDTO){

        return blogService.saveBlog(blogDTO);
    }
    @GetMapping("/display-all-blogs")
    public ResponseEntity<?> getAllBlogs(){

        return blogService.getAllBlogs();
    }
}
