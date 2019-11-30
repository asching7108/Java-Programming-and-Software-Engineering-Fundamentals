
/**
 * Prints movie ratings based on input filters.
 * 
 * @author Hsingyi Lin
 * @version 09/12/2019
 */

import java.util.*;

public class MovieRunnerWithFilters {
    private static String moviefile = "ratedmoviesfull.csv";
    private static String ratingsfile = "ratings.csv";

    private ThirdRatings getRatings() {
        MovieDatabase.initialize(moviefile);
        ThirdRatings ratings = new ThirdRatings(ratingsfile);
        System.out.println("the number of movies: " + MovieDatabase.size());
        System.out.println("the number of raters: " + ratings.getRaterSize());
        return ratings;
    }
    
    public void printAverageRatings() {
        ThirdRatings ratings = getRatings();
        ArrayList<Rating> avgRatings = ratings.getAverageRatings(35);
        System.out.println("the number of movies with average rating: " + avgRatings.size());
        for (int i = 0; i < avgRatings.size(); i++) {
            Rating rating = avgRatings.get(i);
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printAverageRatingsByYear() {
        ThirdRatings ratings = getRatings();
        YearAfterFilter f1 = new YearAfterFilter(2000);
        ArrayList<Rating> avgRatings = ratings.getAverageRatingsByFilter(20, f1);
        System.out.println("the number of movies with average rating: " + avgRatings.size());
        for (int i = 0; i < avgRatings.size(); i++) {
            Rating rating = avgRatings.get(i);
            System.out.println(rating.getValue() + " " + 
                               MovieDatabase.getYear(rating.getItem()) + " " + 
                               MovieDatabase.getTitle(rating.getItem()));
        }        
    }
    
    public void printAverageRatingsByGenre() {
        ThirdRatings ratings = getRatings();
        GenreFilter f1 = new GenreFilter("Comedy");
        ArrayList<Rating> avgRatings = ratings.getAverageRatingsByFilter(20, f1);
        System.out.println("the number of movies with average rating: " + avgRatings.size());
        for (int i = 0; i < avgRatings.size(); i++) {
            Rating rating = avgRatings.get(i);
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(rating.getItem()));
        }        
    }
    
    public void printAverageRatingsByMinutes() {
        ThirdRatings ratings = getRatings();
        MinutesFilter f1 = new MinutesFilter(105, 135);
        ArrayList<Rating> avgRatings = ratings.getAverageRatingsByFilter(5, f1);
        System.out.println("the number of movies with average rating: " + avgRatings.size());
        for (int i = 0; i < avgRatings.size(); i++) {
            Rating rating = avgRatings.get(i);
            System.out.println(rating.getValue() + " Time: " + 
                               MovieDatabase.getMinutes(rating.getItem()) + " " + 
                               MovieDatabase.getTitle(rating.getItem()));
        }                
    }
    
    public void printAverageRatingsByDirectors() {
        ThirdRatings ratings = getRatings();
        DirectorsFilter f1 = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        ArrayList<Rating> avgRatings = ratings.getAverageRatingsByFilter(4, f1);
        System.out.println("the number of movies with average rating: " + avgRatings.size());
        for (int i = 0; i < avgRatings.size(); i++) {
            Rating rating = avgRatings.get(i);
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t" + MovieDatabase.getDirector(rating.getItem()));
        }                
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        ThirdRatings ratings = getRatings();
        AllFilters f1 = new AllFilters();
        f1.addFilter(new YearAfterFilter(1990));
        f1.addFilter(new GenreFilter("Drama"));
        ArrayList<Rating> avgRatings = ratings.getAverageRatingsByFilter(8, f1);
        System.out.println("the number of movies with average rating: " + avgRatings.size());
        for (int i = 0; i < avgRatings.size(); i++) {
            Rating rating = avgRatings.get(i);
            System.out.println(rating.getValue() + " " + 
                               MovieDatabase.getYear(rating.getItem()) + " " + 
                               MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(rating.getItem()));
        }                
    }
    
    public void printAverageRatingsByDirectorsAndMinutes() {
        ThirdRatings ratings = getRatings();
        AllFilters f1 = new AllFilters();
        f1.addFilter(new MinutesFilter(90, 180));
        f1.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
        ArrayList<Rating> avgRatings = ratings.getAverageRatingsByFilter(3, f1);
        System.out.println("the number of movies with average rating: " + avgRatings.size());
        for (int i = 0; i < avgRatings.size(); i++) {
            Rating rating = avgRatings.get(i);
            System.out.println(rating.getValue() + " Time: " + 
                               MovieDatabase.getMinutes(rating.getItem()) + " " + 
                               MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t" + MovieDatabase.getDirector(rating.getItem()));
        }                
    }
}
