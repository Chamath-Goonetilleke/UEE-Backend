package com.uee.backend.IT20122096.Campaigns.Controller;

import com.uee.backend.IT20122096.Campaigns.DTO.CampaignDTO;
import com.uee.backend.IT20122096.Campaigns.DTO.ContributionDTO;
import com.uee.backend.IT20122096.Campaigns.DTO.ContributionResponseDTO;
import com.uee.backend.IT20122096.Campaigns.Entity.Campaign;
import com.uee.backend.IT20122096.Campaigns.Entity.Contribution;
import com.uee.backend.IT20122096.Campaigns.Service.Abstract.CampaignService;
import com.uee.backend.IT20122096.Campaigns.Service.Abstract.ContributionService;
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
    final
    ContributionService contributionService;

    public CampaignController(CampaignService campaignService, ContributionService contributionService) {
        this.campaignService = campaignService;
        this.contributionService = contributionService;
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
        List<Campaign> campaignList = campaignService.getAllCampaignsByUserId(userId);
        if(campaignList !=null){
            return new ResponseEntity<>(campaignList,HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Something went wrong.", HttpStatus.BAD_REQUEST);

        }
    }
    @GetMapping("/getCampaignById/{campId}")
    public ResponseEntity<?> getCampaignById(@PathVariable ObjectId campId){
        Optional<Campaign> campaign = campaignService.getCampaignById(campId);
        if (campaign.isPresent()){
            return new ResponseEntity<>(campaign,HttpStatus.OK);
        }
        return new ResponseEntity<>("Something went wrong.", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/addContribution")
    public ResponseEntity<?> saveContribution(@RequestBody ContributionDTO contributionDTO){
        Contribution contribution = contributionService.saveContribution(contributionDTO);
        if(contribution != null){
            return new ResponseEntity<>("Saved Successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getAllContributors/{campaignId}")
    public ResponseEntity<?> getAllContribution(@PathVariable ObjectId campaignId){
        List<ContributionResponseDTO> contributionResponseList= contributionService.getAllContribution(campaignId);
        if (contributionResponseList!=null){
            return new ResponseEntity<>(contributionResponseList,HttpStatus.OK);

        }else{
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.BAD_REQUEST);

        }
    }
    @PostMapping("/markAttendance")
    public  ResponseEntity<?> markAttendance(@RequestBody List<ContributionDTO> contributionDTO){
        boolean response = contributionService.markAttendance(contributionDTO);
        if (response){
            return new ResponseEntity<>("Saved Successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/updateCampaign")
    public ResponseEntity<?> updateCampaign(@RequestBody CampaignDTO campaignDTO){
        Campaign campaign = campaignService.updateCampaign(campaignDTO);
        if (campaign == null) {
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.BAD_REQUEST);

        }else{
            return new ResponseEntity<>("Saved Successfully", HttpStatus.OK);

        }
    }
}
