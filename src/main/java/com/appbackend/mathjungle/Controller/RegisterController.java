package com.appbackend.mathjungle.Controller;

import com.appbackend.mathjungle.Model.Child;
import com.appbackend.mathjungle.Model.RegisterRequest;
import com.appbackend.mathjungle.Model.Users;
import com.appbackend.mathjungle.Repo.ChildRepo;
import com.appbackend.mathjungle.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest req) {
        // 1. Create a new Users entity
        Users user = new Users();
        user.setEmail(req.getEmail());
        user.setPassword(req.getPassword()); // You could also encode the password here

        // 2. Create Child entity
        Child child = new Child();
        child.setChildName(req.getChildName());
        child.setAge(req.getChildAge());

        // If you want to store avatar in Child:
        // child.setAvatar(req.getAvatar()); // you'll need to add an 'avatar' field in Child

        // 3. Link them
        child.setParent(user);
        user.setChildProfile(child);

        // 4. Save (depending on cascade settings)
        userRepo.save(user);
        // If not using cascade = CascadeType.ALL, you may have to explicitly save child:
        // childRepo.save(child);

        return ResponseEntity.ok("Registration successful!");
    }
}
