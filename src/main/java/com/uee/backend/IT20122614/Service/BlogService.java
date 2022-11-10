package com.uee.backend.IT20122614.Service;

import com.uee.backend.IT20122614.DTO.BlogDTO;
import com.uee.backend.IT20122614.Model.Blog;
import org.springframework.http.ResponseEntity;

public interface BlogService {
    ResponseEntity<?> saveBlog(BlogDTO blogDTO);
    ResponseEntity<?> getAllBlogs();
}
