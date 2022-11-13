package com.uee.backend.IT20122614.Repository;

import com.uee.backend.IT20122614.Model.Blog;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends MongoRepository<Blog, ObjectId> {
}
