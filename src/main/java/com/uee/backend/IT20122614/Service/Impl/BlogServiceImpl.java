package com.uee.backend.IT20122614.Service.Impl;

import com.uee.backend.IT20122614.DTO.BlogDTO;
import com.uee.backend.IT20122614.Model.Blog;
import com.uee.backend.IT20122614.Repository.BlogRepository;
import com.uee.backend.IT20122614.Service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class BlogServiceImpl implements BlogService {
    final
    BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public ResponseEntity<?> saveBlog(BlogDTO blogDTO) {

        try{
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime now = LocalDateTime.now();
            System.out.println(dtf.format(now));

            Blog blogSave = new Blog();
            blogSave.setTitle(blogDTO.getTitle());
            blogSave.setUserName(blogDTO.getUserName());
            blogSave.setImageUrl(blogDTO.getImageUrl());
            blogSave.setContent(blogDTO.getContent());
            blogSave.setTime(dtf.format(now));

            blogRepository.save(blogSave);

            return ResponseEntity.status(HttpStatus.OK).body("Successfully created");


        }catch(Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot create new blog");
        }

    }

    @Override
    public ResponseEntity<?> getAllBlogs() {
        try{
            List<Blog> blogs = blogRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(blogs);

        }catch(Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot create new blog");
        }

    }
}
