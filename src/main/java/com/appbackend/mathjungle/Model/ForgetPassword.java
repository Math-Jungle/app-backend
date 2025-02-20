package com.appbackend.mathjungle.Model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class ForgetPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer forgotPasswordId;

    @Column(nullable = false)
    private Integer otp;

    @Column(nullable = false)
    private Date expirationTime;

    @OneToOne
    private Users user;

    public ForgetPassword() {
    }

    // Private constructor to force the use of the builder
    private ForgetPassword(Integer forgotPasswordId, Integer otp, Date expirationTime, Users user) {
        this.forgotPasswordId = forgotPasswordId;
        this.otp = otp;
        this.expirationTime = expirationTime;
        this.user = user;
    }

    // Getters
    public Integer getForgotPasswordId() {
        return forgotPasswordId;
    }

    public Integer getOtp() {
        return otp;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public Users getUser() {
        return user;
    }

    // Static Builder Class
    public static class Builder {
        private Integer forgotPasswordId;
        private Integer otp;
        private Date expirationTime;
        private Users user;

        // Setter methods in the builder
        public Builder forgotPasswordId(Integer forgotPasswordId) {
            this.forgotPasswordId = forgotPasswordId;
            return this;
        }

        public Builder otp(Integer otp) {
            this.otp = otp;
            return this;
        }

        public Builder expirationTime(Date expirationTime) {
            this.expirationTime = expirationTime;
            return this;
        }

        public Builder user(Users user) {
            this.user = user;
            return this;
        }

        // Build method that returns the ForgetPassword object
        public ForgetPassword build() {
            return new ForgetPassword(forgotPasswordId, otp, expirationTime, user);
        }
    }
    public static Builder builder() {
        return new Builder();
    }

}


//package com.appbackend.mathjungle.Model;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Builder
//public class ForgetPassword {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer forgotPasswordId;
//
//    @Column(nullable = false)
//    private Integer otp;
//
//    @Column(nullable = false)
//    private Date expirationTime;
//
//    @OneToOne
//    private Users user;
//}
