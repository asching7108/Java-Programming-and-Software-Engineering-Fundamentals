
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerAverage {
    public void printAverageRatings() {
        String moviefile = "ratedmoviesfull.csv";
        String ratingsfile = "ratings.csv";
        SecondRatings second = new SecondRatings(moviefile, ratingsfile);
        System.out.println("the number of movies: " + second.getMovieSize());
        System.out.println("the number of raters: " + second.getRaterSize());
        ArrayList<Rating> avgRatings = second.getAverageRatings(3);
        for (int i = 0; i < avgRatings.size(); i++) {
            Rating rating = avgRatings.get(i);
            System.out.println(rating.getValue() + " " + second.getTitle(rating.getItem()));
        }
    }
    
    public void getAverageRatingOneMovie() {
        String moviefile = "ratedmoviesfull.csv";
        String ratingsfile = "ratings.csv";
        SecondRatings second = new SecondRatings(moviefile, ratingsfile);
        String title = "The Godfather";
        String id = second.getID(title);
        double value = second.getAverageByID(id, 3);
        System.out.println("the average rating of the " + title + " is " + value);
    }
}
