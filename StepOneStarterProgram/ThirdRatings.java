
/**
 * Calculates and returns movie ratings based on input filters.
 * 
 * @author Hsingyi Lin
 * @version 09/12/2019
 */

import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        FirstRatings first = new FirstRatings();
        myRaters = first.loadRaters(ratingsfile);
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    public double getAverageByID(String id, int minimalRaters) {
        double sum = 0.0;
        if (!MovieDatabase.containsID(id)) {  // if id is not found, return 0.0
            return 0.0;
        }
        Movie movie = MovieDatabase.getMovie(id);
        // compute the number of raters of movie
        int numRater = 0;
        for (Rater r : myRaters) {
            if (r.hasRating(id)) {
                numRater++;
                sum += r.getRating(id);
            }
        }
        // if the number of raters is less than minimalRaters, return 0.0
        if (numRater < minimalRaters) {
            return 0.0;
        }
        return sum / numRater;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();
        for (String id : movies) {
            double avg = getAverageByID(id, minimalRaters);
            if (avg > 0.0) {
                Rating avgRating = new Rating(id, avg);
                avgRatings.add(avgRating);
            }
        }
        // sort by ratings, lowest to highest
        Collections.sort(avgRatings, new RatingComparator());
        return avgRatings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();
        for (String id : movies) {
            double avg = getAverageByID(id, minimalRaters);
            if (avg > 0.0) {
                Rating avgRating = new Rating(id, avg);
                avgRatings.add(avgRating);
            }
        }
        // sort by ratings, lowest to highest
        Collections.sort(avgRatings, new RatingComparator());
        return avgRatings;
    }
}
