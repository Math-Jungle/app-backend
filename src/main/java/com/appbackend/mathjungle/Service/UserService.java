package com.appbackend.mathjungle.Service;

import com.appbackend.mathjungle.Model.Users;
import com.appbackend.mathjungle.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public void registerUser(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);

    }

    public boolean checkUserDetails(Users user) {

        Users userLogin = userRepo.findByEmail(user.getEmail());

        if (userLogin != null) {
            return true;
        }else {
            return false;
        }
    }

    public String verify(Users user) {

        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        if (auth.isAuthenticated()) {
            return jwtService.generateToken(user.getEmail());

        }
        return "Failed";

    }

       public Users getUserDetailsByUsername(String email) {
        return userRepo.findByEmail(email);
    }

    public boolean deleteUser(int userID) {
        if (userRepo.existsById(userID)) {
            userRepo.deleteById(userID);
            return true;
        }
        return false;
    }
}
