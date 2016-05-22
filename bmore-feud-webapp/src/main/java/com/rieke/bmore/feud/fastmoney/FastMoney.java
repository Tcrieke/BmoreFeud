package com.rieke.bmore.feud.fastmoney;

import com.rieke.bmore.feud.category.Category;

import java.io.Serializable;

/**
 * Created by tylerrieke on 4/16/16.
 */
public class FastMoney implements Serializable {

    private static final long serialVersionUID = 0L;
    public static final int NUM_CATEGORIES = 5;
    public static boolean active = true;

    public static final int PLAYER_1 = 1;
    public static final int PLAYER_2 = 2;

    public static FastMoneyPlayer player1 = new FastMoneyPlayer(PLAYER_1);
    public static FastMoneyPlayer player2 = new FastMoneyPlayer(PLAYER_2);

    public static FastMoneyPlayer currentPlayer = new FastMoneyPlayer(PLAYER_1);


    public static int selectedCategoryIndex = 0;


    public int getNumCategories() {
        return NUM_CATEGORIES;
    }

    public FastMoneyPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(FastMoneyPlayer currentPlayer) {
        FastMoney.currentPlayer = currentPlayer;
    }

    public FastMoneyPlayer getPlayer1() {
        return player1;
    }

    public void setPlayer1(FastMoneyPlayer player1) {
        FastMoney.player1 = player1;
    }

    public FastMoneyPlayer getPlayer2() {
        return player2;
    }

    public void setPlayer2(FastMoneyPlayer player2) {
        FastMoney.player2 = player2;
    }

    public int getSelectedCategoryIndex() {
        return selectedCategoryIndex;
    }

    public static void setSelectedCategoryIndex(int selectedCategoryIndex) {
        FastMoney.selectedCategoryIndex = selectedCategoryIndex;
    }

    public static void iterateNextCategoryIndex() {
        int index = (selectedCategoryIndex+1)%NUM_CATEGORIES;
        boolean foundEmpty = false;
        while(index!=selectedCategoryIndex) {
            String currentAnswer = currentPlayer.getAnswers()[index].getAnswer();
            if(currentAnswer==null || currentAnswer.isEmpty()) {
                selectedCategoryIndex=index;
                foundEmpty=true;
                break;
            }
            index = (index+1)%NUM_CATEGORIES;
        }

        if(!foundEmpty) {
            selectedCategoryIndex = (selectedCategoryIndex+1)%NUM_CATEGORIES;
        }
    }

    public int getTotalScore() {
        int pointsScored = 0;
        for(FastMoneyAnswer answer:player1.getAnswers()) {
            if(answer!=null && answer.isValueVisible()) {
                pointsScored += answer.getPoints();
            }
        }
        for(FastMoneyAnswer answer:player2.getAnswers()) {
            if(answer!=null && answer.isValueVisible()) {
                pointsScored += answer.getPoints();
            }
        }
        return pointsScored;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        FastMoney.active = active;
    }
}
