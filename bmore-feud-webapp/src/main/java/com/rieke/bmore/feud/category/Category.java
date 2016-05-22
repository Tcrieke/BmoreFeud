package com.rieke.bmore.feud.category;

import com.rieke.bmore.feud.value.Value;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tcrieke on 3/15/15.
 */
public class Category implements Serializable{
    private static final long serialVersionUID = 0L;

    private int id;
    private String name;
    private List<Value> values;

    public Category() {
        values = new ArrayList<>();
    }

    public Category(Category category) {
        id = category.getId();
        name = new String(category.getName());
        values = new ArrayList<>();
        for(Value value:category.getValues()) {
            values.add(new Value(value));
        }
    }

    public Category(String name, List<Value> values) {
        this.name = name;
        this.values = (values==null?new ArrayList<Value>():values);
    }

    public Category(int id, String name, List<Value> values) {
        this.id = id;
        this.name = name;
        this.values = (values==null?new ArrayList<Value>():values);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;

        Category category = (Category) o;

        if (!name.equals(category.name)) return false;
        if (!values.equals(category.values)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + values.hashCode();
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }
}
