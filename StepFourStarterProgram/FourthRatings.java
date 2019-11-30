
/**
 * Calculates and returns weighted movie ratings based on input filters and similarities.
 * 
 * @author Hsingyi Lin
 * @version 09/13/2019
 */

import java.util.*;

public class FourthRatings {    
    public double getAverageByID(String id, int minimalRaters) {
        double sum = 0.0;
        if (!MovieDatabase.containsID(id)) {  // if id is not found, return 0.0
            return 0.0;
        }
        Movie movie = MovieDatabase.getMovie(id);
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        // compute the number of raters of movie
        int numRater = 0;
        for (Rater r : raters) {
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

    public double getWeightedAverageByID(String id, int minimalRaters, ArrayList<Rating> similarities) {
        double sum = 0.0;
        if (!MovieDatabase.containsID(id)) {  // if id is not found, return 0.0
            return 0.0;
        }
        Movie movie = MovieDatabase.getMovie(id);
        ArrayList<Rater> topSimilarRaters = new ArrayList<Rater>();
        for (int i = 0; i < similarities.size(); i++) {
            topSimilarRaters.add(RaterDatabase.getRater(similarities.get(i).getItem()));
        }
        // compute the number of raters of movie
        int numRater = 0;
        for (int i = 0; i < similarities.size(); i++) {
            Rater r = topSimilarRaters.get(i);
            if (r.hasRating(id)) {
                numRater++;
                sum += r.getRating(id) * similarities.get(i).getValue();
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
    
    private double dotProduct(Rater me, Rater r) {
        double dot_product = 0.0;
        if (me == null || r == null) {
            return -9999;
        }
        if (me.equals(r)) {
            return 0.0;
        }
        ArrayList<String> myRating = me.getItemsRated();
        for (String item : myRating) {
            if (r.hasRating(item)) {
                dot_product += (me.getRating(item) - 5) * (r.getRating(item) - 5);
            }
        }
        return dot_product;
    }
    
    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> similarities = new ArrayList<Rating>();
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        for (Rater r : raters) {
            double dot_product = dotProduct(RaterDatabase.getRater(id), r);
            if (dot_product > 0) {
                Rating similarity = new Rating(r.getID(), dot_product);
                similarities.add(similarity);
            }
        }
        Collections.sort(similarities, Collections.reverseOrder(new RatingComparator()));
        return similarities;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> similarities = getSimilarities(id);
        // get topSimilarities with numSimilarRaters
        ArrayList<Rating> topSimilarities = new ArrayList<Rating>();
        for (int i = 0; i < Math.min(numSimilarRaters, similarities.size()); i++) {
            topSimilarities.add(similarities.get(i));
        }
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();
        // get weighted average rating for each movie
        for (String item : movies) {
            double avg = getWeightedAverageByID(item, minimalRaters, topSimilarities);
            if (avg > 0.0) {
                Rating avgRating = new Rating(item, avg);
                avgRatings.add(avgRating);
            }
        }
        // sort by ratings, lowest to highest
        Collections.sort(avgRatings, Collections.reverseOrder(new RatingComparator()));
        return avgRatings;        
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, 
                                                       int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> similarities = getSimilarities(id);
        // get topSimilarities with numSimilarRaters
        ArrayList<Rating> topSimilarities = new ArrayList<Rating>();
        for (int i = 0; i < Math.min(numSimilarRaters, similarities.size()); i++) {
            topSimilarities.add(similarities.get(i));
        }
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();
        // get weighted average rating for each movie
        for (String item : movies) {
            double avg = getWeightedAverageByID(item, minimalRaters, topSimilarities);
            if (avg > 0.0) {
                Rating avgRating = new Rating(item, avg);
                avgRatings.add(avgRating);
            }
        }
        // sort by ratings, lowest to highest
        Collections.sort(avgRatings, Collections.reverseOrder(new RatingComparator()));
        return avgRatings;        
    }
}
