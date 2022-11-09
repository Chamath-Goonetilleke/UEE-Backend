package com.uee.backend.IT20122096.Points.Service.Impl;

import com.uee.backend.IT20122096.LoginRegistrationAuth.Entity.User;
import com.uee.backend.IT20122096.LoginRegistrationAuth.Service.UserService;
import com.uee.backend.IT20122096.Points.DTO.PointDTO;
import com.uee.backend.IT20122096.Points.Entity.Point;
import com.uee.backend.IT20122096.Points.Repository.PointRepository;
import com.uee.backend.IT20122096.Points.Service.PointService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class PointServiceImpl implements PointService {

    final PointRepository pointRepository;
    final UserService userService;

    Calendar cal = Calendar.getInstance();


    public PointServiceImpl(PointRepository pointRepository, UserService userService) {
        this.pointRepository = pointRepository;
        this.userService = userService;
    }

    @Override
    public Point updatePoint(ObjectId userID, int point) {

        cal.setTime(new Date());
        String year = Year.now().toString();
        int month = cal.get(Calendar.MONTH);
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        Point point1 = pointRepository.getPointByUserId(userID, months[month], year);

        point1.setPoints(point + point1.getPoints());
        return pointRepository.save(point1);
    }

    @Override
    public List<PointDTO> getAllPoints() {

        try {
            List<Point> points = pointRepository.findAll();
            List<PointDTO> pointList = new ArrayList<>();
            for (Point point : points) {
                PointDTO pointDTO = new PointDTO();
                pointDTO.setId(point.getId());

                ResponseEntity<?> userResponse = userService.getUserById(point.getUserId());
                if (userResponse.getStatusCodeValue() == HttpStatus.OK.value()) {
                    User user= (User) userResponse.getBody();
                    assert user != null;
                    user.setPassword(null);
                    pointDTO.setUser(user);
                }

                pointDTO.setYear(point.getYear());
                pointDTO.setMonth(point.getMonth());
                pointDTO.setPoints(point.getPoints());

                pointList.add(pointDTO);

            }
            return pointList;
        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public PointDTO getPointByUserId(ObjectId userId) {
        try {
            cal.setTime(new Date());
            String year = Year.now().toString();
            int month = cal.get(Calendar.MONTH);
            String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

            Point point = pointRepository.getPointByUserId(userId, months[month], year);
            PointDTO pointDTO = new PointDTO();
            ResponseEntity<?> userResponse = userService.getUserById(point.getUserId());
            if (userResponse.getStatusCodeValue() == HttpStatus.OK.value()) {
                User user= (User) userResponse.getBody();
                assert user != null;
                user.setPassword(null);
                pointDTO.setUser(user);
            }

            pointDTO.setYear(point.getYear());
            pointDTO.setMonth(point.getMonth());
            pointDTO.setPoints(point.getPoints());
            return pointDTO;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public List<PointDTO> getAllPointsByUserId(ObjectId userId) {

        try {
            List<Point> points = pointRepository.getAllPointsByUserId(userId);
            List<PointDTO> pointList = new ArrayList<>();
            for (Point point : points) {
                PointDTO pointDTO = new PointDTO();
                pointDTO.setId(point.getId());

                ResponseEntity<?> userResponse = userService.getUserById(point.getUserId());
                if (userResponse.getStatusCodeValue() == HttpStatus.OK.value()) {
                    User user= (User) userResponse.getBody();
                    assert user != null;
                    user.setPassword(null);
                    pointDTO.setUser(user);
                }

                pointDTO.setYear(point.getYear());
                pointDTO.setMonth(point.getMonth());
                pointDTO.setPoints(point.getPoints());

                pointList.add(pointDTO);

            }
            return pointList;

        } catch (Exception e) {
            return null;
        }
    }

}
