package com.appbackend.mathjungle.Controller;


import com.appbackend.mathjungle.Model.Users;
import com.appbackend.mathjungle.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Users>register(@RequestBody Users user){
        userService.registerUser(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/login")
    public ResponseEntity<Users>login(@RequestBody Users user){
        userService.verify(user);
        return ResponseEntity.ok(user);
    }


}
