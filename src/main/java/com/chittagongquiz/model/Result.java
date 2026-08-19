package com.chittagongquiz.model;

import java.sql.Timestamp;

public class Result {
    private int id;
    private String playerName;
    private int score;
    private int totalQuestions;
    private Timestamp attemptTime;

    public Result() {}

    public Result(String playerName, int score, int totalQuestions) {
        this.playerName = playerName;
        this.score = score;
        this.totalQuestions = totalQuestions;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public int getTotalQuestions() { return totalQuestions; }
    public void setTotalQuestions(int totalQuestions) { this.totalQuestions = totalQuestions; }

    public Timestamp getAttemptTime() { return attemptTime; }
    public void setAttemptTime(Timestamp attemptTime) { this.attemptTime = attemptTime; }
}
