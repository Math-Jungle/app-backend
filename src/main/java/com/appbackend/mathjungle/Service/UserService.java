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

        Users userLogin = userRepo.findByUsername(user.getUserName());

        if (userLogin != null) {
            return true;
        }else {
            return false;
        }
    }

    public String verify(Users user) {

        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));

        if (auth.isAuthenticated()) {
            jwtService.generateToken(user.getUserName());
            return "Success";
        }
        return "Failed";

    }
}
