package com.appbackend.mathjungle.Model;

import jakarta.persistence.Entity;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterRequest {

    private String email;
    private String password;
    private String childName;
    private int childAge;
//    private String avatar;


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

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public int getChildAge() {
        return childAge;
    }

    public void setChildAge(int childAge) {
        this.childAge = childAge;
    }



}
