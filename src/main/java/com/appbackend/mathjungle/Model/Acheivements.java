package com.appbackend.mathjungle.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.util.Date;

@Entity
public class Acheivements {

    @Id
    private int id;
    private int score;
    private int timeTake;
    private Date date;
    private String description;

    @OneToOne
    private Child child;


}
