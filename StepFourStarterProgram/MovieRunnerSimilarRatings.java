
/**
 * Prints weighted movie ratings based on input filters and similarities.
 * 
 * @author Hsingyi Lin
 * @version 09/13/2019
 */

import java.util.*;

public class MovieRunnerSimilarRatings {
    private static String moviefile = "ratedmoviesfull.csv";
    private static String ratingsfile = "ratings.csv";

    private FourthRatings getRatings() {
        MovieDatabase.initialize(moviefile);
        RaterDatabase.initialize(ratingsfile);
        FourthRatings ratings = new FourthRatings();
        System.out.println("the number of movies: " + MovieDatabase.size());
        System.out.println("the number of raters: " + RaterDatabase.size());
        return ratings;
    }

    public void printAverageRatings() {
        FourthRatings ratings = getRatings();
        ArrayList<Rating> avgRatings = ratings.getAverageRatings(1);
        System.out.println("the number of movies with average rating: " + avgRatings.size());
        for (int i = 0; i < avgRatings.size(); i++) {
            Rating rating = avgRatings.get(i);
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printAverageRatingsByYearAfterAndGenre() {
        FourthRatings ratings = getRatings();
        AllFilters f1 = new AllFilters();
        f1.addFilter(new YearAfterFilter(1980));
        f1.addFilter(new GenreFilter("Romance"));
        ArrayList<Rating> avgRatings = ratings.getAverageRatingsByFilter(1, f1);
        System.out.println("the number of movies with average rating: " + avgRatings.size());
        for (int i = 0; i < avgRatings.size(); i++) {
            Rating rating = avgRatings.get(i);
            System.out.println(rating.getValue() + " " + 
                               MovieDatabase.getYear(rating.getItem()) + " " + 
                               MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(rating.getItem()));
        }                
    }
    
    public void printSimilarRatings() {
        FourthRatings ratings = getRatings();
        ArrayList<Rating> avgRatings = ratings.getSimilarRatings("71", 20, 5);
        System.out.println("the number of movies with average rating: " + avgRatings.size());
        for (int i = 0; i < avgRatings.size(); i++) {
            Rating rating = avgRatings.get(i);
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenre() {
        FourthRatings ratings = getRatings();
        GenreFilter f1 = new GenreFilter("Mystery");
        ArrayList<Rating> avgRatings = ratings.getSimilarRatingsByFilter("964", 20, 5, f1);
        System.out.println("the number of movies with average rating: " + avgRatings.size());
        for (int i = 0; i < avgRatings.size(); i++) {
            Rating rating = avgRatings.get(i);
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(rating.getItem()));
        }        
    }

    public void printSimilarRatingsByDirector() {
        FourthRatings ratings = getRatings();
        DirectorsFilter f1 = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        ArrayList<Rating> avgRatings = ratings.getSimilarRatingsByFilter("120", 10, 2, f1);
        System.out.println("the number of movies with average rating: " + avgRatings.size());
        for (int i = 0; i < avgRatings.size(); i++) {
            Rating rating = avgRatings.get(i);
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t" + MovieDatabase.getDirector(rating.getItem()));
        }                
    }
    
    public void printSimilarRatingsByGenreAndMinutes() {
        FourthRatings ratings = getRatings();
        AllFilters f1 = new AllFilters();
        f1.addFilter(new GenreFilter("Drama"));
        f1.addFilter(new MinutesFilter(80, 160));
        ArrayList<Rating> avgRatings = ratings.getSimilarRatingsByFilter("168", 10, 3, f1);
        System.out.println("the number of movies with average rating: " + avgRatings.size());
        for (int i = 0; i < avgRatings.size(); i++) {
            Rating rating = avgRatings.get(i);
            System.out.println(rating.getValue() + " Time: " + 
                               MovieDatabase.getMinutes(rating.getItem()) + " " + 
                               MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(rating.getItem()));
        }                
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes() {
        FourthRatings ratings = getRatings();
        AllFilters f1 = new AllFilters();
        f1.addFilter(new MinutesFilter(70, 200));
        f1.addFilter(new YearAfterFilter(1975));
        ArrayList<Rating> avgRatings = ratings.getSimilarRatingsByFilter("314", 10, 5, f1);
        System.out.println("the number of movies with average rating: " + avgRatings.size());
        for (int i = 0; i < avgRatings.size(); i++) {
            Rating rating = avgRatings.get(i);
            System.out.println(rating.getValue() + " Time: " + 
                               MovieDatabase.getMinutes(rating.getItem()) + " " + 
                               MovieDatabase.getYear(rating.getItem()) + " " + 
                               MovieDatabase.getTitle(rating.getItem()));
        }                
    }
}
