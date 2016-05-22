package com.rieke.bmore.feud.fastmoney;

/**
 * Created by tylerrieke on 4/16/16.
 */
public class FastMoneyAnswer {

    private String answer="";
    private int points;
    private boolean visible;
    private boolean valueVisible;


    public FastMoneyAnswer() {

    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isValueVisible() {
        return valueVisible;
    }

    public void setValueVisible(boolean valueVisible) {
        this.valueVisible = valueVisible;
    }
}
