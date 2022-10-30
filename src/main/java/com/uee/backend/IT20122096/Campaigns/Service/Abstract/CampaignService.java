package com.uee.backend.IT20122096.Campaigns.Service.Abstract;

import com.uee.backend.IT20122096.Campaigns.DTO.CampaignDTO;
import com.uee.backend.IT20122096.Campaigns.Entity.Campaign;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface CampaignService {

    Campaign saveCampaign(CampaignDTO campaignDTO);
    List<Campaign> getAllCampaigns();
    List<Campaign> getAllCampaignsByUserId(ObjectId userId);
    Optional<Campaign> getCampaignById(ObjectId campId);
    Campaign updateCampaign(CampaignDTO campaignDTO);


}
