
/**
 * Efficient data structure of raters.
 * 
 * @author Hsingyi Lin
 * @version 09/12/2019
 */

import java.util.*;

public class EfficientRater implements Rater {
    private String myID;
    private HashMap<String,Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String,Rating>();
    }

    public void addRating(String item, double rating) {
        myRatings.put(item, new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        return myRatings.containsKey(item);
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        if (myRatings.containsKey(item)) {
            return myRatings.get(item).getValue();
        }        
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        return new ArrayList<String>(myRatings.keySet());
    }
    
    @Override
    public boolean equals(Object o) {
        EfficientRater other = (EfficientRater) o;
        if (myID.equals(other.getID())) {
            return true;
        }
        return false;
    }
    
    public String toString() {
        return myID;
    }
}
