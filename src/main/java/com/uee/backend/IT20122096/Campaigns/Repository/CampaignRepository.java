package com.uee.backend.IT20122096.Campaigns.Repository;

import com.uee.backend.IT20122096.Campaigns.Entity.Campaign;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends MongoRepository<Campaign, ObjectId> {

    @Query("{'host': ?0}")
    List<Campaign> getAllCampaignsByUserId(ObjectId userId);

}
