package com.rieke.bmore.feud.audience;

import java.io.Serializable;

/**
 * Created by tcrieke on 3/27/15.
 */
public class PollCategory implements Serializable,Comparable<PollCategory> {
    private static final long serialVersionUID = 0L;

    private int id;
    private String name;
    private int votes;

    public PollCategory() {

    }

    public PollCategory(int id, String name, int votes) {
        this.id = id;
        this.name = name;
        this.votes = votes;
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

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PollCategory)) return false;

        PollCategory that = (PollCategory) o;

        if (id != that.id) return false;
        if (votes != that.votes) return false;
        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + votes;
        return result;
    }

    @Override
    public int compareTo(PollCategory object) {
        if (this.getVotes() > object.getVotes())
            return -1;
        if (this.getVotes()==object.getVotes())
            return 0;
        return 1;
    }

}
