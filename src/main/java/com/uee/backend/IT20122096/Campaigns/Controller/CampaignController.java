package com.uee.backend.IT20122096.Campaigns.Controller;

import com.uee.backend.IT20122096.Campaigns.DTO.CampaignDTO;
import com.uee.backend.IT20122096.Campaigns.Entity.Campaign;
import com.uee.backend.IT20122096.Campaigns.Service.Abstract.CampaignService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/campaign")
public class CampaignController {

    final
    CampaignService campaignService;

    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveCampaign(@RequestBody CampaignDTO campaignDTO){
        Campaign campaign = campaignService.saveCampaign(campaignDTO);
        if(campaign==null){
            return new ResponseEntity<>("Something went wrong.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(campaign,HttpStatus.OK);
    }

    @GetMapping("/getAllCampaigns")
    public ResponseEntity<?> getAllCampaign(){
        List<Campaign> campaignList = campaignService.getAllCampaigns();
        if(campaignList.isEmpty()){
            return new ResponseEntity<>("Something went wrong.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(campaignList,HttpStatus.OK);
    }

    @GetMapping("/getAllCampaignsByUser/{userId}")
    public ResponseEntity<?> getAllCampaignsByUserId(@PathVariable ObjectId userId){
        List<Campaign> campaignList = campaignService.getAllCampaigns();
        if(campaignList.isEmpty()){
            return new ResponseEntity<>("Something went wrong.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(campaignList,HttpStatus.OK);
    }
    @GetMapping("/getCampaignById/{campId}")
    public ResponseEntity<?> getCampaignById(@PathVariable ObjectId campId){
        Optional<Campaign> campaign = campaignService.getCampaignById(campId);
        if (campaign.isPresent()){
            return new ResponseEntity<>(campaign,HttpStatus.OK);
        }
        return new ResponseEntity<>("Something went wrong.", HttpStatus.BAD_REQUEST);

    }
}
