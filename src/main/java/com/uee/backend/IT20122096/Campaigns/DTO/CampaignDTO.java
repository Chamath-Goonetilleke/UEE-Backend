package com.uee.backend.IT20122096.Campaigns.DTO;

import com.uee.backend.IT20122096.Campaigns.Entity.Campaign;
import lombok.*;
import org.bson.types.ObjectId;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CampaignDTO {

    private ObjectId id;
    private ObjectId host;
    private String place;
    private String date;
    private String startTime;
    private String endTime;
    private String description;
    private String image;
    private Campaign.Status status= Campaign.Status.PENDING;
}
