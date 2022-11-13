package com.uee.backend.IT20122096.Points.Controller;

import com.uee.backend.IT20122096.Points.DTO.PointDTO;
import com.uee.backend.IT20122096.Points.Service.PointService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/point")
public class PointController {

    final
    PointService pointService;

    public PointController(PointService pointService) {
        this.pointService = pointService;
    }

    @GetMapping("/getAllPoints")
    public ResponseEntity<?> getAllPoints() {
        List<PointDTO> pointList = pointService.getAllPoints();
        if (pointList==null){
            return new ResponseEntity<>("Something went wrong.", HttpStatus.BAD_REQUEST);

        }else {
            return new ResponseEntity<>(pointList,HttpStatus.OK);

        }
    }

    @GetMapping("/getAllPointsByUserId/{userId}")
    public ResponseEntity<?> getAllPointsByUserId(@PathVariable ObjectId userId) {
        List<PointDTO> pointList = pointService.getAllPointsByUserId(userId);
        if (pointList==null){
            return new ResponseEntity<>("Something went wrong.", HttpStatus.BAD_REQUEST);

        }else {
            return new ResponseEntity<>(pointList,HttpStatus.OK);

        }
    }
    @GetMapping("/getPointByUserId/{userId}")
    public ResponseEntity<?> getPointByUserId(@PathVariable ObjectId userId) {
        PointDTO point = pointService.getPointByUserId(userId);
        if (point==null){
            return new ResponseEntity<>("Something went wrong.", HttpStatus.BAD_REQUEST);

        }else {
            return new ResponseEntity<>(point,HttpStatus.OK);

        }
    }

}
