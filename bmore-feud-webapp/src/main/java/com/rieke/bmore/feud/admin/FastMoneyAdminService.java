package com.rieke.bmore.feud.admin;

import com.rieke.bmore.feud.board.Gameboard;
import com.rieke.bmore.feud.category.Category;
import com.rieke.bmore.feud.category.CategoryService;
import com.rieke.bmore.feud.fastmoney.FastMoney;
import com.rieke.bmore.feud.fastmoney.FastMoneyAnswer;
import com.rieke.bmore.feud.fastmoney.FastMoneyPlayer;
import com.rieke.bmore.feud.value.Value;

import java.security.InvalidParameterException;

/**
 * Created by tylerrieke on 4/16/16.
 */
public class FastMoneyAdminService {

    private CategoryService categoryService;
    private FastMoney fastMoney;

    public FastMoneyAdminService(CategoryService categoryService) {
        this.categoryService = categoryService;
        fastMoney = new FastMoney();
        newRound();
    }

    public FastMoney getGameBoard() {
        return fastMoney;
    }

    public void newRound() {
        Category[] p1Categories = new Category[FastMoney.NUM_CATEGORIES];
        Category[] p2Categories = new Category[FastMoney.NUM_CATEGORIES];
        FastMoneyAnswer[] p1Answers = new FastMoneyAnswer[FastMoney.NUM_CATEGORIES];
        FastMoneyAnswer[] p2Answers = new FastMoneyAnswer[FastMoney.NUM_CATEGORIES];

        for(int i =0; i <FastMoney.NUM_CATEGORIES;i++) {
            p1Categories[i] = categoryService.getNextCategory();
            p2Categories[i] = new Category(p1Categories[i]);
            p1Answers[i] = new FastMoneyAnswer();
            p2Answers[i] = new FastMoneyAnswer();
        }
        FastMoneyPlayer player1 = new FastMoneyPlayer(1,p1Categories,p1Answers, true);
        fastMoney.setPlayer1(player1);
        fastMoney.setPlayer2(new FastMoneyPlayer(2,p2Categories, p2Answers, true));
        fastMoney.setCurrentPlayer(player1);
    }

    public void flipCategoryValue(int categoryId, String value, boolean visible) {
        int index=0;
        for (Category category : fastMoney.getCurrentPlayer().getCategories()) {
            if (category.getId() == categoryId) {
                for (Value fieldValue : category.getValues()) {
                    if (fieldValue.getValue().equals(value)) {
                        fieldValue.setVisible(visible);
                        int points = (visible?fieldValue.getPoints():0);
                        fastMoney.getCurrentPlayer().getAnswers()[index].setPoints(points);
                    } else if(visible) {
                        fieldValue.setVisible(false);
                    }
                }
                break;
            }
            index++;
        }
    }

    public void updateAnswer(Integer answer,String value,Boolean visible, Boolean valueVisible) {


        if(answer==null || answer>=FastMoney.NUM_CATEGORIES) {
            throw new InvalidParameterException("Answer index must be an integer less than: "+FastMoney.NUM_CATEGORIES);
        }

        if(value!=null) {
            fastMoney.getCurrentPlayer().getAnswers()[answer].setAnswer(value);
        }

        if(visible!=null) {
            fastMoney.getCurrentPlayer().getAnswers()[answer].setVisible(visible);
        }

        if (valueVisible!=null) {
            fastMoney.getCurrentPlayer().getAnswers()[answer].setValueVisible(valueVisible);
        }
    }

    public void setCurrentPlayer(int player) {
        fastMoney.setCurrentPlayer(getPlayer(player));
    }

    public void setSelectedCategory(int categoryIndex) {
        fastMoney.setSelectedCategoryIndex(categoryIndex);
    }

    public void goToFamilyFeud() {
        FastMoney.active=false;
        Gameboard.active=true;
    }

    private FastMoneyPlayer getPlayer(int player) {
        FastMoneyPlayer fastMoneyPlayer;
        if(player==1) {
            fastMoneyPlayer = fastMoney.getPlayer1();
        } else if(player==2) {
            fastMoneyPlayer = fastMoney.getPlayer2();
        } else {
            throw new InvalidParameterException("Must be player=1 or player=2");
        }
        return fastMoneyPlayer;
    }
}
