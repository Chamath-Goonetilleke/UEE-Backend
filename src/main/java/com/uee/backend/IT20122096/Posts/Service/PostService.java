package com.uee.backend.IT20122096.Posts.Service;

import com.uee.backend.IT20122096.Posts.DTO.PostDTO;
import com.uee.backend.IT20122096.Posts.DTO.PostResponseDTO;
import com.uee.backend.IT20122096.Posts.Entity.Post;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Post savePost(PostDTO postDTO);
    List<PostResponseDTO> getAllPosts();
    List<PostResponseDTO> getAllPostsByUserId(ObjectId userId);
    PostResponseDTO getPostsById(ObjectId postId);
    Boolean deletePost(ObjectId postId);

}
