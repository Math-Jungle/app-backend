package com.appbackend.mathjungle.Controller;


import com.appbackend.mathjungle.Model.Users;
import com.appbackend.mathjungle.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

//    @PostMapping("/register")
//    public ResponseEntity<Users>register(@RequestBody Users user){
//        userService.registerUser(user);
//        return ResponseEntity.ok(user);
//    }

    @PostMapping("/login")
    public ResponseEntity<String>login(@RequestBody Users user){
        String token = userService.verify(user);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/details")
    public ResponseEntity<Users>details(Principal principal){
        // 'principal' is populated by Spring Security once your JwtFilter sets the security context

        if (principal != null) {
            String username = principal.getName();
            Users user = userService.getUserDetailsByUsername(username);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }
    @GetMapping("/delete/{userID}")
    public ResponseEntity<Users>delete(Principal principal , @PathVariable int userID){
        if (principal != null) {
            String username = principal.getName();
            boolean status =userService.deletUser(userID);
            if(status){
                return ResponseEntity.ok(userService.getUserDetailsByUsername(username));
            }

        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/verifyJWT")
    public ResponseEntity<String>verifyJWT(Principal principal){
        if (principal == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
