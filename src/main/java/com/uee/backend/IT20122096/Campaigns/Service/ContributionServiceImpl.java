package com.uee.backend.IT20122096.Campaigns.Service;

import com.uee.backend.IT20122096.Campaigns.DTO.ContributionDTO;
import com.uee.backend.IT20122096.Campaigns.DTO.ContributionResponseDTO;
import com.uee.backend.IT20122096.Campaigns.Entity.Contribution;
import com.uee.backend.IT20122096.Campaigns.Repository.ContributionRepository;
import com.uee.backend.IT20122096.Campaigns.Service.Abstract.ContributionService;
import com.uee.backend.IT20122096.LoginRegistrationAuth.Entity.User;
import com.uee.backend.IT20122096.LoginRegistrationAuth.Service.UserService;
import com.uee.backend.IT20122096.Points.Service.PointService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ContributionServiceImpl implements ContributionService {

    final
    ContributionRepository contributionRepository;
    final
    UserService userService;
    final
    PointService pointService;

    public ContributionServiceImpl(ContributionRepository contributionRepository, UserService userService, PointService pointService) {
        this.contributionRepository = contributionRepository;
        this.userService = userService;
        this.pointService = pointService;
    }

    @Override
    public Contribution saveContribution(ContributionDTO contributionDTO) {

        Contribution contribution = new Contribution();
        contribution.setUserId(contributionDTO.getUserId());
        contribution.setCampaignId(contributionDTO.getCampaignId());
        contribution.setFirstName(contributionDTO.getFirstName());
        contribution.setLastName(contributionDTO.getLastName());
        contribution.setContactNo(contributionDTO.getContactNo());
        contribution.setContribution(contributionDTO.getContribution());

        try {
            return contributionRepository.save(contribution);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ContributionResponseDTO> getAllContribution(ObjectId campaignId) {

        try {
            List<Contribution> contributions = contributionRepository.getAllContributionCampaignId(campaignId);
            List<ContributionResponseDTO> contributionResponseList = new ArrayList<>();

            for (Contribution contribution : contributions) {

                ContributionResponseDTO contributionResponseDTO = new ContributionResponseDTO();
                contributionResponseDTO.setId(contribution.getId());
                contributionResponseDTO.setUserId(contribution.getUserId());
                contributionResponseDTO.setCampaignId(campaignId);
                contributionResponseDTO.setName(contribution.getFirstName().concat(" " + contribution.getLastName()));
                contributionResponseDTO.setAttendance(contribution.getAttendance());
                ResponseEntity<?> userRes = userService.getUserById(contribution.getUserId());
                if (userRes.getStatusCodeValue() == HttpStatus.OK.value()) {
                    User user = (User) userRes.getBody();
                    assert user != null;
                    user.setPassword(null);
                    contributionResponseDTO.setUser(user);

                }
                contributionResponseList.add(contributionResponseDTO);
            }
            return contributionResponseList;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean markAttendance(List<ContributionDTO> contributionDTO) {
        try {
            for (ContributionDTO cont:contributionDTO
                 ) {
                Optional<Contribution> contributionOptional= contributionRepository.findById(cont.getId());
                Contribution contribution=contributionOptional.get();
                if(cont.getAttendance().equals(Contribution.Status.PRESENT)){
                    contribution.setAttendance(Contribution.Status.PRESENT);
                    /**
                     * award points for contribution*/
                    pointService.updatePoint(cont.getUserId(),20);
                }
                else {
                    contribution.setAttendance(Contribution.Status.ABSENT);
                }
                contributionRepository.save(contribution);
            }
            return true;

        } catch (Exception e) {
            return false;

        }
    }
}
