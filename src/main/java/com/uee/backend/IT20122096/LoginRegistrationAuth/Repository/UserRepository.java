package com.uee.backend.IT20122096.LoginRegistrationAuth.Repository;


import com.uee.backend.IT20122096.LoginRegistrationAuth.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface UserRepository extends MongoRepository<User, ObjectId> {

    @Query("{'email' : ?0}")
    User findUserByEmail (String email);

}
