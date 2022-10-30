package com.uee.backend.IT20122096.Campaigns.DTO;

import com.uee.backend.IT20122096.Campaigns.Entity.Contribution;
import lombok.*;
import org.bson.types.ObjectId;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ContributionDTO {
    private ObjectId id;
    private ObjectId user;
    private ObjectId campaign;
    private String firstName;
    private String lastName;
    private String contactNo;
    private String contribution;
    private Contribution.Status attendance = Contribution.Status.ABSENT;
}
