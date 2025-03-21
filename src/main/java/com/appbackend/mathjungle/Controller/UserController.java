package com.appbackend.mathjungle.Controller;


import com.appbackend.mathjungle.DTO.HomePageDTO;
import com.appbackend.mathjungle.Mapper.HomePageMapper;
import com.appbackend.mathjungle.Model.Users;
import com.appbackend.mathjungle.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/login")
    public ResponseEntity<String>login(@RequestBody Users user){
        String token = userService.verify(user);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/details")
    public ResponseEntity<?>details(Principal principal){

        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        }
        String username = principal.getName();
        Users user = userService.getUserDetailsByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        HomePageDTO data = HomePageMapper.getData(user);
        return ResponseEntity.ok(data);


    }
    @GetMapping("/delete/{userID}")
    public ResponseEntity<Users>delete(Principal principal , @PathVariable int userID){
        if (principal != null) {
            String username = principal.getName();
            boolean status =userService.deleteUser(userID);
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
