package com.rieke.bmore.feud.value;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tcrieke on 3/15/15.
 */
public class Value implements Serializable {
    private static final long serialVersionUID = 0L;

    private int id;
    private String value;
    private int points;
    private List<String> acceptedValues;
    private boolean visible = false;

    public Value() {
        acceptedValues = new ArrayList<String>();
    }

    public Value(Value value) {
        id = value.getId();
        this.value = value.getValue();
        points = value.getPoints();
        acceptedValues = value.getAcceptedValues();
        visible = new Boolean(value.isVisible());
    }

    public Value(String value, int points, List<String> acceptedValues) {
        this.value = value;
        this.points = points;
        this.acceptedValues = (acceptedValues==null?new ArrayList<String>():acceptedValues);
    }

    public Value(int id, String value, int points, List<String> acceptedValues) {
        this.id = id;
        this.value = value;
        this.points = points;
        this.acceptedValues = (acceptedValues==null?new ArrayList<String>():acceptedValues);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Value)) return false;

        Value value1 = (Value) o;

        if (points != value1.points) return false;
        if (!acceptedValues.equals(value1.acceptedValues)) return false;
        if (!value.equals(value1.value)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = value.hashCode();
        result = 31 * result + points;
        result = 31 * result + acceptedValues.hashCode();
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    public List<String> getAcceptedValues() {
        return acceptedValues;
    }

    public void setAcceptedValues(List<String> acceptedValues) {
        this.acceptedValues = acceptedValues;
    }
}
