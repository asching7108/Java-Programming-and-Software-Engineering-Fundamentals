
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerWithFilters {
    private static String moviefile = "ratedmoviesfull.csv";
    private static String ratingsfile = "data/ratings.csv";

    private ThirdRatings getRatings() {
        MovieDatabase.initialize(moviefile);
        ThirdRatings ratings = new ThirdRatings(ratingsfile);
        System.out.println("the number of movies: " + MovieDatabase.size());
        System.out.println("the number of raters: " + ratings.getRaterSize());
        return ratings;
    }
    
    public void printAverageRatings() {
        ThirdRatings ratings = getRatings();
        ArrayList<Rating> avgRatings = ratings.getAverageRatings(8);
        System.out.println("the number of movies with average rating: " + avgRatings.size());
        for (int i = 0; i < avgRatings.size(); i++) {
            Rating rating = avgRatings.get(i);
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printAverageRatingsByYear() {
        ThirdRatings ratings = getRatings();
        YearAfterFilter f1 = new YearAfterFilter(2000);
        ArrayList<Rating> avgRatings = ratings.getAverageRatingsByFilter(1, f1);
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
        GenreFilter f1 = new GenreFilter("Crime");
        ArrayList<Rating> avgRatings = ratings.getAverageRatingsByFilter(1, f1);
        System.out.println("the number of movies with average rating: " + avgRatings.size());
        for (int i = 0; i < avgRatings.size(); i++) {
            Rating rating = avgRatings.get(i);
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(rating.getItem()));
        }        
    }
    
    public void printAverageRatingsByMinutes() {
        ThirdRatings ratings = getRatings();
        MinutesFilter f1 = new MinutesFilter(110, 170);
        ArrayList<Rating> avgRatings = ratings.getAverageRatingsByFilter(1, f1);
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
        DirectorsFilter f1 = new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze");
        ArrayList<Rating> avgRatings = ratings.getAverageRatingsByFilter(1, f1);
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
    
    public void printAverageRatingsByDirectorsAndMinutes() {
        ThirdRatings ratings = getRatings();
        AllFilters f1 = new AllFilters();
        f1.addFilter(new MinutesFilter(30, 170));
        f1.addFilter(new DirectorsFilter("Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola"));
        ArrayList<Rating> avgRatings = ratings.getAverageRatingsByFilter(1, f1);
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
