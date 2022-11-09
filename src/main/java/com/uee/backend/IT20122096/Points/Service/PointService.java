package com.uee.backend.IT20122096.Points.Service;

import com.uee.backend.IT20122096.Points.DTO.PointDTO;
import com.uee.backend.IT20122096.Points.Entity.Point;
import org.bson.types.ObjectId;

import java.util.List;

public interface PointService {


    Point updatePoint(ObjectId userID, int point);
    List<PointDTO> getAllPoints();
    PointDTO getPointByUserId(ObjectId userId);
    List<PointDTO> getAllPointsByUserId(ObjectId userId);

}
