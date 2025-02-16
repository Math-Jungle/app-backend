package com.appbackend.mathjungle.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Users {

    @Id
    @GeneratedValue
    private int userID;
    private String userName;
    private String password;
    private String email;
    
    @OneToOne(mappedBy = "parent")
    private Child childProfile;

    @OneToOne(mappedBy = "user")
    private ForgetPassword forgotPassword;


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ForgetPassword getForgotPassword() {
        return forgotPassword;
    }

    public void setForgotPassword(ForgetPassword forgotPassword) {
        this.forgotPassword = forgotPassword;
    }
    public Child getChildProfile() {
        return childProfile;
    }

    public void setChildProfile(Child childProfile) {
        this.childProfile = childProfile;
    }
    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
