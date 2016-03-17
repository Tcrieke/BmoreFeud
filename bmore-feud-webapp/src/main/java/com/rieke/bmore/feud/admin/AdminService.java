package com.rieke.bmore.feud.admin;

import com.rieke.bmore.feud.audience.AudienceService;
import com.rieke.bmore.feud.audience.PollCategory;
import com.rieke.bmore.feud.board.Gameboard;
import com.rieke.bmore.feud.board.GameMode;
import com.rieke.bmore.feud.category.Category;
import com.rieke.bmore.feud.category.CategoryService;
import com.rieke.bmore.feud.value.Value;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tcrieke on 3/16/15.
 */
public class AdminService {

    private static final int MAX_WRONG = 3;
    private static final int ROUNDS = 3;

    private CategoryService categoryService;
    private AudienceService audienceService;
    private Gameboard gameboard;

    public AdminService(CategoryService categoryService, AudienceService audienceService) {
        this.categoryService = categoryService;
        this.audienceService = audienceService;
        gameboard = new Gameboard();
        newRound();
    }

    public Gameboard getGameBoard() {
        return gameboard;
    }

    public void setValueVisible(String value, boolean visible) {
        Category category = gameboard.getCategory();
        boolean success = false;
        boolean allFlipped = true;
        if(category!=null) {
            for(Value valueObj:category.getValues()) {
                if(valueObj.getValue().equals(value)) {
                    valueObj.setVisible(visible);
                    success = true;
                }
                if(!valueObj.isVisible()) {
                    allFlipped=false;
                }
            }
            if(success) {
                switch(gameboard.getGameMode()) {
                    case NORMAL:
                        if(allFlipped) {
                            roundOver(true);
                        }
                        break;
                    case REBUTTAL:
                        roundOver(false);
                        break;
                }
            }
        }

    }

    public void setPlayingTeam(int playingTeam) {
        gameboard.setTeamPlaying(playingTeam);
        switchMode(GameMode.NORMAL);
    }

    public void markIncorrect(Boolean quiet) {
        switch(gameboard.getGameMode()) {
            case NORMAL:
                Gameboard.newIncorrect(quiet);
                if(gameboard.getIncorrectCount()>=MAX_WRONG) {
                    switchMode(GameMode.REBUTTAL);
                }
                break;
            case REBUTTAL:
                Gameboard.newIncorrect(quiet);
                roundOver(true);
                break;
            case FACEOFF:
                Gameboard.newIncorrect(quiet,1);
                break;
            default:
        }
    }

    private void switchMode(GameMode mode) {
        gameboard.setIncorrectCount(0);
        gameboard.setGameMode(mode);
    }

    public void switchMode(String mode) {
        GameMode gameMode;
        try {
            gameMode = GameMode.valueOf(mode);
        } catch (IllegalArgumentException e) {
            gameMode = GameMode.NORMAL;
        }
        switchMode(gameMode);
    }

    public void roundOver(boolean playingTeamWon) {

        int pointsScored = gameboard.getRoundScore();

        int playingTeam = gameboard.getTeamPlaying();
        if((playingTeamWon && playingTeam==1) || (!playingTeamWon && playingTeam!=1)) {
            gameboard.setTeam1Score(gameboard.getTeam1Score() + pointsScored);
        } else {
            gameboard.setTeam2Score(gameboard.getTeam2Score() + pointsScored);
        }
        switchMode(GameMode.ROUNDOVER);
    }

    public void newRound() {
        if(gameboard.getGameMode()==GameMode.ROUNDOVER) {
            gameboard.setRound(gameboard.getRound() + 1);
        }
        List<PollCategory> categories = audienceService.getPolledCategories();
        Category category;
        if(categories!=null && !categories.isEmpty()) {
            category = categoryService.getCategory(categories.get(0).getId());
        } else {
            category = categoryService.getNextCategory();
        }
        gameboard.setCategory(category);
        gameboard.setTeamPlaying(0);
        switchMode(GameMode.FACEOFF);

        Map<Integer,Category> poll = new HashMap<>();

        for(int i = 0; i < 8; i ++) {
            category = categoryService.getNextCategory();
            poll.put(category.getId(),category);
        }

        Gameboard.setNextCategories(poll);
        Gameboard.clearNextCategoryVotes();
    }
}
