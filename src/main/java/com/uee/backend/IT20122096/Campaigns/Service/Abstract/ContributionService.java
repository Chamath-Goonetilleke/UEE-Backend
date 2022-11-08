package com.uee.backend.IT20122096.Campaigns.Service.Abstract;

import com.uee.backend.IT20122096.Campaigns.DTO.ContributionDTO;
import com.uee.backend.IT20122096.Campaigns.DTO.ContributionResponseDTO;
import com.uee.backend.IT20122096.Campaigns.Entity.Contribution;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface ContributionService {

    Contribution saveContribution(ContributionDTO contributionDTO);
    List<ContributionResponseDTO> getAllContribution(ObjectId campaignId);
    Boolean markAttendance(List<ContributionDTO> contributionDTO) ;

}
