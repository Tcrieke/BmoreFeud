package com.rieke.bmore.feud.fastmoney;

import com.rieke.bmore.feud.category.Category;

/**
 * Created by tylerrieke on 4/22/16.
 */
public class FastMoneyPlayer {

    private int id;
    private Category[] categories = new Category[FastMoney.NUM_CATEGORIES];
    private FastMoneyAnswer[] answers = new FastMoneyAnswer[FastMoney.NUM_CATEGORIES];
    private boolean visible = true;

    public FastMoneyPlayer(int id) {
        this.id = id;
    }

    public FastMoneyPlayer(int id, Category[] categories, FastMoneyAnswer[] answers, boolean visible) {
        this.id = id;
        this.categories = categories;
        this.answers = answers;
        this.visible = visible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category[] getCategories() {
        return categories;
    }

    public void setCategories(Category[] categories) {
        this.categories = categories;
    }

    public FastMoneyAnswer[] getAnswers() {
        return answers;
    }

    public void setAnswers(FastMoneyAnswer[] answers) {
        this.answers = answers;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
