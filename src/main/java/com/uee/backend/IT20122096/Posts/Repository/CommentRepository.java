package com.uee.backend.IT20122096.Posts.Repository;

import com.uee.backend.IT20122096.Posts.Entity.Comment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, ObjectId> {

    @Query("{'postId': ?0}")
    List<Comment> getAllCommentsByPostId(ObjectId postId);
}
