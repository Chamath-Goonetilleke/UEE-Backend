package com.uee.backend.IT20122614.Repository;

import com.uee.backend.IT20122614.Model.Complain_Comment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends MongoRepository<Complain_Comment, ObjectId> {
    Object findAllById(String id);


    List<Complain_Comment> findByComplainId(String id);
}
