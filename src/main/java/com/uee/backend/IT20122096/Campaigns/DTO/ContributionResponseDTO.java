package com.uee.backend.IT20122096.Campaigns.DTO;

import com.uee.backend.IT20122096.Campaigns.Entity.Contribution;
import com.uee.backend.IT20122096.LoginRegistrationAuth.Entity.User;
import lombok.*;
import org.bson.types.ObjectId;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ContributionResponseDTO {
    private ObjectId id;
    private ObjectId userId;
    private ObjectId campaignId;
    private String name;
    private User user;
    private Contribution.Status attendance = Contribution.Status.ABSENT;
}
