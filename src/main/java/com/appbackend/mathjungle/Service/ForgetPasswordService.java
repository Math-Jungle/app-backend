package com.appbackend.mathjungle.Service;

import com.appbackend.mathjungle.Model.ForgetPassword;
import com.appbackend.mathjungle.Model.MailBody;
import com.appbackend.mathjungle.Model.Users;
import com.appbackend.mathjungle.Repo.ForgetPasswordRepository;
import com.appbackend.mathjungle.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Service
public class ForgetPasswordService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    EmailService emailService;

    @Autowired
    ForgetPasswordRepository forgetPasswordRepo;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public boolean emailVerification(String email) {

        Users user = userRepo.findByEmail(email);
        if (user == null) {
            System.out.println("Please enter a valid email address");
            return false;
        }
        else{ int otp = otpGenerator();
            MailBody mailBody = MailBody.builder()
                    .to(email)
                    .body("This is the OTP for your Password request : " + otp)
                    .subject("OTP for Forget request")
                    .build();
            ForgetPassword  fp = ForgetPassword.builder()
                    .otp(otp)
                    .expirationTime(new Date(System.currentTimeMillis() +70 *1000))
                    .build();
            emailService.sendSimpleMessage(mailBody);
            forgetPasswordRepo.save(fp);
            return true;
        }

           }

    private int otpGenerator() {
        Random rand = new Random();
        return rand.nextInt(100000 , 999999);
    }

    public boolean verifyOtp(String email, Integer otp) {
        Users user = userRepo.findByEmail(email);
        if (user == null) {
            System.out.println("Please enter a valid email address");
            return false;
        }

        System.out.println("Valid email address");
        Optional<ForgetPassword> fp = forgetPasswordRepo.findByOtpAndUser(otp, user);
        if (fp.isEmpty()) {
            System.out.println("OTP does not exist");
            return false;
        }
        ForgetPassword forgetPassword = fp.get();


        if (forgetPassword.getExpirationTime().before(Date.from(Instant.now()))) {
            // OTP expired, delete the record and return false
            System.out.println("OTP expired");
            forgetPasswordRepo.deleteById(forgetPassword.getForgotPasswordId());
            return false;
        }

        // OTP is valid and not expired, return true
        System.out.println("OTP verified successfully");
        return true;
    }

    public void changePassword(String email, String password) {

        String encodedPassword = encoder.encode(password);
        userRepo.updatePassword(email, encodedPassword);

    }
}
