package com.appbackend.mathjungle.Model;

import jakarta.persistence.*;
import java.util.List;

import java.util.Date;

@Entity
public class DashBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String levelName;
    private int score;

    @ElementCollection
    private List<Float> reactionTimes;

    private String timeStamp;

    @OneToOne
    @JoinColumn(referencedColumnName = "userID")
    private Users user;

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Float> getReactionTimes() {
        return reactionTimes;
    }

    public void setReactionTimes(List<Float> reactionTimes) {
        this.reactionTimes = reactionTimes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
