package com.appbackend.mathjungle.Controller;

import com.appbackend.mathjungle.Model.Child;
import com.appbackend.mathjungle.Model.RegisterRequest;
import com.appbackend.mathjungle.Model.Users;
import com.appbackend.mathjungle.Repo.ChildRepo;
import com.appbackend.mathjungle.Repo.UserRepo;
import com.appbackend.mathjungle.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RegisterController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ChildRepo childRepo; // if you have a separate child repo (or rely on cascading)

    @Autowired
    UserService userService;
    

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest req) {

        Users user = new Users();
        user.setEmail(req.getEmail());
        user.setPassword(req.getPassword());

        Child child = new Child();
        child.setChildName(req.getChildName());
        child.setAge(req.getChildAge());
        child.setParent(user);
        user.setChildProfile(child);

        userService.registerUser(user);

        return ResponseEntity.ok("Registration successful!");
    }
}
