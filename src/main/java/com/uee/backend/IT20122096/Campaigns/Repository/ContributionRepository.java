package com.uee.backend.IT20122096.Campaigns.Repository;

import com.uee.backend.IT20122096.Campaigns.Entity.Contribution;
import com.uee.backend.IT20122096.Posts.Entity.Comment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ContributionRepository extends MongoRepository<Contribution, ObjectId> {

    @Query("{'campaignId': ?0}")
    List<Contribution> getAllContributionCampaignId(ObjectId campaignId);

    @Query("{'campaignId': ?0 , 'userId': ?1}")
    Contribution getContributionByCampAndUserId(ObjectId campaignId,ObjectId userId);

}
