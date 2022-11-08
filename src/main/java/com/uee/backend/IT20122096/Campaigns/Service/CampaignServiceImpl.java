package com.uee.backend.IT20122096.Campaigns.Service;

import com.uee.backend.IT20122096.Campaigns.DTO.CampaignDTO;
import com.uee.backend.IT20122096.Campaigns.Entity.Campaign;
import com.uee.backend.IT20122096.Campaigns.Repository.CampaignRepository;
import com.uee.backend.IT20122096.Campaigns.Service.Abstract.CampaignService;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class CampaignServiceImpl implements CampaignService {

    final CampaignRepository campaignRepository;

    public CampaignServiceImpl(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public Campaign saveCampaign(CampaignDTO campaignDTO) {
        try {
            Campaign campaign = new Campaign();
            campaign.setHost(campaignDTO.getHost());
            campaign.setPlace(campaignDTO.getPlace());
            campaign.setDate(campaignDTO.getDate());
            campaign.setStartTime(campaignDTO.getStartTime());
            campaign.setEndTime(campaignDTO.getEndTime());
            campaign.setDescription(campaignDTO.getDescription());
            campaign.setImage(campaignDTO.getImage());

            return campaignRepository.save(campaign);
        } catch (Exception e) {
            return null;
        }


    }

    @Override
    public List<Campaign> getAllCampaigns() {
        try {
            return campaignRepository.findAll();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Campaign> getAllCampaignsByUserId(ObjectId userId) {
        try {
            return campaignRepository.getAllCampaignsByUserId(userId);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Optional<Campaign> getCampaignById(ObjectId campId) {
        try {
            return campaignRepository.findById(campId);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Campaign updateCampaign(CampaignDTO campaignDTO) {
        try {
            Optional<Campaign> campaignOptional = campaignRepository.findById(campaignDTO.getId());
            Campaign campaign = campaignOptional.get();
            switch (campaignDTO.getStatus()) {
                case FINISH -> campaign.setStatus(Campaign.Status.FINISH);
                case PENDING -> campaign.setStatus(Campaign.Status.PENDING);
                case HAPPENING -> campaign.setStatus(Campaign.Status.HAPPENING);
            }
            return campaignRepository.save(campaign);
        }catch (Exception e){
            return null;
        }
    }
}
