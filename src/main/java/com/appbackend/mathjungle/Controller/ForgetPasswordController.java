package com.appbackend.mathjungle.Controller;

import com.appbackend.mathjungle.Repo.UserRepo;
import com.appbackend.mathjungle.Service.EmailService;
import com.appbackend.mathjungle.Service.ForgetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forgetPassword")
public class ForgetPasswordController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ForgetPasswordService forgetPasswordService;


    @PostMapping("/verifyMail/{email}")
    public ResponseEntity<String> verifyMail(@PathVariable String email) {

        boolean verificationStatus =forgetPasswordService.emailVerification(email);
        if (verificationStatus) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>("email not found",HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/verifyOtp/{otp}/{email}")
    public ResponseEntity<String> verifyOtp(@PathVariable Integer otp,@PathVariable String email) {
        boolean verificationStatus =forgetPasswordService.verifyOtp(email,otp);
        if (verificationStatus) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/newPassword/{email}")
    public ResponseEntity<String> newPassword(@RequestBody String changePassword, @PathVariable String newPassword) {

        return new ResponseEntity<>("Password is changed",HttpStatus.OK);


    }
}
