package com.rieke.bmore.feud.audience;

import com.rieke.bmore.feud.board.Gameboard;
import com.rieke.bmore.feud.category.Category;

import java.util.*;

/**
 * Created by tcrieke on 3/27/15.
 */
public class AudienceService {


    public boolean voteForCategory(String ip, Integer categoryId) {
        boolean success = false;
        if(categoryId!=null && categoryId==0) {
            Gameboard.getNextCategoryVotes().remove(ip);
        } else if(isValidVote(categoryId)) {
            Gameboard.getNextCategoryVotes().put(ip,categoryId);
            success = true;
        }
        return success;
    }

    public int getVoteForIp(String ip) {
        Integer vote = 0;
        Map<String,Integer> votes = Gameboard.getNextCategoryVotes();
        if(votes!=null) {
            vote = votes.get(ip);
            if (!isValidVote(vote)) {
                vote = 0;
            }
        }
        return vote;
    }

    public List<PollCategory> getPolledCategories() {
        Map<Integer,Category> categories = Gameboard.getNextCategories();
        Map<String,Integer> votes = Gameboard.getNextCategoryVotes();

        Map<Integer, Integer> result = new TreeMap<Integer, Integer>();

        for (Map.Entry<String,Integer> entry:votes.entrySet()) {
            Integer value = entry.getValue();
            Integer count = result.get(value);
            if (count == null)
                result.put(value, new Integer(1));
            else
                result.put(value, new Integer(count+1));
        }

        List<PollCategory> pollCategories = new ArrayList<PollCategory>();

        for(Category category:categories.values()) {
            Integer count = result.get(category.getId());
            count = (count==null?0:count);
            pollCategories.add(new PollCategory(category.getId(),category.getName(),count));
        }

        Collections.sort(pollCategories);

        return pollCategories;
    }

    private boolean isValidVote(Integer vote) {
        boolean valid = false;
        if(vote!=null) {
            Map<Integer,Category> categories = Gameboard.getNextCategories();
            valid = categories.keySet().contains(vote);
        }
        return valid;
    }
}
