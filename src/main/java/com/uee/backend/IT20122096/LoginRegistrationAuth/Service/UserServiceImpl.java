package com.uee.backend.IT20122096.LoginRegistrationAuth.Service;


import com.uee.backend.IT20122096.LoginRegistrationAuth.DTO.UserRegisterDTO;
import com.uee.backend.IT20122096.LoginRegistrationAuth.DTO.UserUpdateDTO;
import com.uee.backend.IT20122096.LoginRegistrationAuth.Repository.UserRepository;
import com.uee.backend.IT20122096.Points.Entity.Point;
import com.uee.backend.IT20122096.Points.Repository.PointRepository;
import com.uee.backend.IT20122096.Points.Service.PointService;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


import java.time.Year;
import java.util.*;

@Component
public class UserServiceImpl implements UserDetailsService, UserService {
    private final UserRepository userRepository;

    final
    PointRepository pointRepository;

    public UserServiceImpl(UserRepository userRepository, PointRepository pointRepository) {
        this.userRepository = userRepository;
        this.pointRepository = pointRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        com.uee.backend.IT20122096.LoginRegistrationAuth.Entity.User user = userRepository.findUserByEmail(email);


        return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    @Override
    public ResponseEntity<List<com.uee.backend.IT20122096.LoginRegistrationAuth.Entity.User>> getAllUsers() {
        List<com.uee.backend.IT20122096.LoginRegistrationAuth.Entity.User> allUsers = userRepository.findAll();

        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getUserById(ObjectId id) {
        Optional<com.uee.backend.IT20122096.LoginRegistrationAuth.Entity.User> user = userRepository.findById(id);
        if (user.isPresent()) {

            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("User dose not exist", HttpStatus.BAD_REQUEST);
    }

    @Override
    public String getUserByEmail(String email) {
        com.uee.backend.IT20122096.LoginRegistrationAuth.Entity.User user = userRepository.findUserByEmail(email);

        return user.getId().toHexString();
    }


    @Override
    public ResponseEntity<?> saveUser(UserRegisterDTO userRegisterDTO) {

        com.uee.backend.IT20122096.LoginRegistrationAuth.Entity.User user = userRepository.findUserByEmail(userRegisterDTO.getEmail());
        if (user != null) {
            return new ResponseEntity<>("User Already Exist", HttpStatus.BAD_REQUEST);
        }

        com.uee.backend.IT20122096.LoginRegistrationAuth.Entity.User visitor = new com.uee.backend.IT20122096.LoginRegistrationAuth.Entity.User(userRegisterDTO.getId(), userRegisterDTO.getName(), userRegisterDTO.getEmail(), userRegisterDTO.getPassword(), null, null, "file:///data/user/0/host.exp.exponent/cache/ImagePicker/98b2c012-2077-4911-8a7d-cc58fee84512.jpeg");
        visitor.setPassword(visitor.passwordEncoder(userRegisterDTO.getPassword()));


        Date date = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String  year = Year.now().toString();
        int month = cal.get(Calendar.MONTH);

        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        com.uee.backend.IT20122096.LoginRegistrationAuth.Entity.User user1 = userRepository.save(visitor);

        Point point= new Point();
        point.setUserId(user1.getId());
        point.setYear(year);
        point.setMonth(months[month]);

        savePoint(point);


        return new ResponseEntity<>(user1, HttpStatus.OK);
    }
    @Override
    public Point savePoint(Point point) {
        return pointRepository.save(point);
    }


    @Override
    public ResponseEntity<?> updateUser(UserUpdateDTO userDTO) {

        Optional<com.uee.backend.IT20122096.LoginRegistrationAuth.Entity.User> user1 = userRepository.findById(userDTO.getId());
        if (user1.isPresent()) {
            com.uee.backend.IT20122096.LoginRegistrationAuth.Entity.User user = user1.get();
            if (userDTO.getCurrentPassword() != null && !(user.isPasswordMatch(userDTO.getCurrentPassword(), user.getPassword()))) {
                return new ResponseEntity<>("Wrong Password", HttpStatus.BAD_REQUEST);
            }

            user.setName(userDTO.getName());
            user.setAddress(userDTO.getAddress());
            user.setPhoneNumber(userDTO.getPhoneNumber());
            user.setImage(userDTO.getImage());

            if (userDTO.getPassword() != null) {
                String encodedPassword = user.passwordEncoder(userDTO.getPassword());
                user.setPassword(encodedPassword);
            }

            return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
        }
        return new ResponseEntity<>("User dose not exist", HttpStatus.BAD_REQUEST);
    }
}
