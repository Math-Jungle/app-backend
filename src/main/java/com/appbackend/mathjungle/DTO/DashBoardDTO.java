package com.appbackend.mathjungle.DTO;

import java.util.List;

public class DashBoardDTO {

    private int id;
    private String levelName;
    private int score;
    private List<Float> reactionTimes;
    private String timeStamp;

    private int userId;

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

    public List<Float> getReactionTimes() {
        return reactionTimes;
    }

    public void setReactionTimes(List<Float> reactionTimes) {
        this.reactionTimes = reactionTimes;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
