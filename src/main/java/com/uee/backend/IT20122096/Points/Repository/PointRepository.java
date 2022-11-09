package com.uee.backend.IT20122096.Points.Repository;

import com.uee.backend.IT20122096.Points.Entity.Point;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PointRepository extends MongoRepository<Point, ObjectId> {

    @Query("{'userId': ?0}")
    List<Point> getAllPointsByUserId(ObjectId userId);

    @Query("{'userId': ?0,'month': ?1,'year': ?2}")
    Point getPointByUserId(ObjectId userId,String month, String year);

}
