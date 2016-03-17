package com.rieke.bmore.feud.board;

import com.rieke.bmore.feud.category.Category;
import com.rieke.bmore.feud.value.Value;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tcrieke on 3/16/15.
 */
public class Gameboard implements Serializable {

    private static final long serialVersionUID = 0L;

    public static Category category = null;
    public static GameMode gameMode = null;
    public static int incorrectCount = 0;
    public static int showIncorrect = 1;

    public static int round = 0;

    public static String team1Name = "Team 1";
    public static String team2Name = "Team 2";
    public static int team1Score = 0;
    public static int team2Score = 0;

    public static int teamPlaying = 1;

    public static Map<String,Integer> nextCategoryVotes = new HashMap<String, Integer>();
    public static Map<Integer,Category> nextCategories = new HashMap<Integer, Category>();

    public static Map<String,Integer> getNextCategoryVotes() {
        return nextCategoryVotes;
    }

    public static Map<Integer,Category> getNextCategories() {
        return nextCategories;
    }

    public static void setNextCategories(Map<Integer,Category> categories) {
        nextCategories = categories;
    }

    public static void clearNextCategoryVotes() {
        nextCategoryVotes.clear();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        Gameboard.category = category;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        Gameboard.gameMode = gameMode;
    }

    public int getIncorrectCount() {
        return incorrectCount;
    }

    public void setIncorrectCount(int incorrectCount) {
        Gameboard.incorrectCount = incorrectCount;
    }

    public static void newIncorrect(boolean quiet) {
        if(!quiet) {
            Gameboard.showIncorrect++;
        }
        Gameboard.incorrectCount++;
    }

    public static void newIncorrect(boolean quiet, int incorrectCount) {
        if(!quiet) {
            Gameboard.showIncorrect++;
        }
        Gameboard.incorrectCount=incorrectCount;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        Gameboard.round = round;
    }

    public String getTeam2Name() {
        return team2Name;
    }

    public void setTeam2Name(String team2Name) {
        Gameboard.team2Name = team2Name;
    }

    public String getTeam1Name() {
        return team1Name;
    }

    public void setTeam1Name(String team1Name) {
        Gameboard.team1Name = team1Name;
    }

    public int getTeam1Score() {
        return team1Score;
    }

    public void setTeam1Score(int team1Score) {
        Gameboard.team1Score = team1Score;
    }

    public int getTeam2Score() {
        return team2Score;
    }

    public void setTeam2Score(int team2Score) {
        Gameboard.team2Score = team2Score;
    }

    public int getTeamPlaying() {
        return teamPlaying;
    }

    public void setTeamPlaying(int teamPlaying) {
        Gameboard.teamPlaying = teamPlaying;
    }

    public int getIncorrectValue() {
        return showIncorrect;
    }

    public void setIncorrectValue(int showIncorrect) {
        Gameboard.showIncorrect=showIncorrect;
    }

    public int getRoundScore() {
        Category category = getCategory();
        int pointsScored = 0;
        for(Value value:category.getValues()) {
            if(value.isVisible()) {
                pointsScored+=value.getPoints();
            }
        }
        return pointsScored;
    }
}
