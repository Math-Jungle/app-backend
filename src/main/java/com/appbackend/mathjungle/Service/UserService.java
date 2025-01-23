package com.appbackend.mathjungle.Service;

import com.appbackend.mathjungle.Model.Users;
import com.appbackend.mathjungle.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

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
}
