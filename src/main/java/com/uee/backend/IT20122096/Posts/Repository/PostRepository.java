package com.uee.backend.IT20122096.Posts.Repository;

import com.uee.backend.IT20122096.Posts.Entity.Post;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, ObjectId> {

    @Query("{'userId': ?0}")
    List<Post> getAllPostsByUserId(ObjectId userId);
}
