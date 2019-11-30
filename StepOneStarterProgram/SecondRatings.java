
/**
 * Calculates and returns movie ratings.
 * 
 * @author Hsingyi Lin
 * @version 09/12/2019
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings first = new FirstRatings();
        myMovies = first.loadMovies(moviefile);
        myRaters = first.loadRaters(ratingsfile);
    }
    
    public int getMovieSize() {
        return myMovies.size();
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    private int indexOfMyMoviesByID(String id) {
        for (int i = 0; i < myMovies.size(); i++) {
            if (myMovies.get(i).getID().equals(id)) {
                return i;
            }
        }
        return -1;
    }
    
    public double getAverageByID(String id, int minimalRaters) {
        double sum = 0.0;
        // get movie from myMovies by ID
        int index = indexOfMyMoviesByID(id);
        if (index == -1) {  // if id is not found, return 0.0
            return 0.0;
        }
        Movie movie = myMovies.get(index);
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
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();
        for (Movie movie : myMovies) {
            double avg = getAverageByID(movie.getID(), minimalRaters);
            if (avg > 0.0) {
                Rating avgRating = new Rating(movie.getID(), avg);
                avgRatings.add(avgRating);
            }
        }
        // sort by ratings, lowest to highest
        for (int i = 0; i < avgRatings.size(); i++) {
            int minIdx = getSmallestAvgRatingIndex(avgRatings, i);
            Rating ri = avgRatings.get(i);
            Rating rmin = avgRatings.get(minIdx);
            avgRatings.set(i, rmin);
            avgRatings.set(minIdx, ri);
        }
        return avgRatings;
    }
    
    public int getSmallestAvgRatingIndex(ArrayList<Rating> avgRatings, int from) {
        int minIdx = from;
        for (int i = from + 1; i < avgRatings.size(); i++) {
            if (avgRatings.get(i).getValue() < avgRatings.get(minIdx).getValue()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public String getTitle(String id) {
        int index = indexOfMyMoviesByID(id);
        if (index == -1) {  // if id is not found, return a message
            return "NO SUCH ID";
        }
        return myMovies.get(index).getTitle();
    }
    
    public String getID(String title) {
        for (int i = 0; i < myMovies.size(); i++) {
            if (myMovies.get(i).getTitle().equals(title)) {
                return myMovies.get(i).getID();
            }
        }
        return "NO SUCH TITLE";        
    }
}
