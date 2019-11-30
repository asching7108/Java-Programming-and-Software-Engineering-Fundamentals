
/**
 * Calculates and returns movie ratings.
 * 
 * @author Hsingyi Lin
 * @version 09/12/2019
 */

import edu.duke.*;
import java.util.*;
import java.nio.file.*;
import java.io.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        for (CSVRecord rec : fr.getCSVParser()) {
            Movie movie = new Movie(rec.get("id"), 
                                    rec.get("title"),
                                    rec.get("year"),
                                    rec.get("genre"),
                                    rec.get("director"),
                                    rec.get("country"),
                                    rec.get("poster"),
                                    Integer.parseInt(rec.get("minutes")));
            movies.add(movie);
        }
        return movies;
    }
    
    public ArrayList<Rater> loadRaters(String filename) {
        ArrayList<Rater> raters = new ArrayList<Rater>();
        FileResource fr = new FileResource(filename);
        for (CSVRecord rec : fr.getCSVParser()) {
            Rater rater = new EfficientRater(rec.get("rater_id"));
            int index = raters.indexOf(rater);
            if (index != -1) {
                rater = raters.get(index);
                raters.remove(index);
            }
            rater.addRating(rec.get("movie_id"),
                            Double.parseDouble(rec.get("rating")));
            raters.add(rater);
        }
        return raters;
    }
    
    public void testLoadMovies() {
        String filename = "ratedmoviesfull.csv";
        ArrayList<Movie> movies = loadMovies(filename);
        System.out.println("the total number of movies: " + movies.size());
        // find the number of movies including the Comedy genre
        System.out.print("the number of movies including the Comedy genre: ");
        int comedy = 0;
        for (Movie mov : movies) {
            if (mov.getGenres().contains("Comedy")) {
                comedy++;
            }
        }
        System.out.println(comedy);
        // find the number of movies greater than 150 minutes
        System.out.print("the number of movies greater than 150 minutes: ");
        int morethan150 = 0;
        for (Movie mov : movies) {
            if (mov.getMinutes() > 150) {
                morethan150++;
            }
        }
        System.out.println(morethan150);
        // find the maximum number of movies by any director and the name of that director(s)
        // compute the number of movies by each director
        System.out.print("the maximum number of movies by any director: ");
        HashMap<String,Integer> movByDrct = new HashMap<String,Integer>();
        for (Movie mov : movies) {
            if (!movByDrct.containsKey(mov.getDirector())) {
                movByDrct.put(mov.getDirector(), 1);
            }
            else {
                movByDrct.put(mov.getDirector(), movByDrct.get(mov.getDirector()) + 1);
            }
        }
        // find the maximum number of movies
        int maxByDrct = 0;
        for (String drct : movByDrct.keySet()) {
            if (movByDrct.get(drct) > maxByDrct) {
                maxByDrct = movByDrct.get(drct);
            }
        }
        // find the director(s) with the maximum number of movies
        ArrayList<String> maxDrcts = new ArrayList<String>();
        for (String drct : movByDrct.keySet()) {
            if (movByDrct.get(drct) == maxByDrct) {
                maxDrcts.add(drct);
            }
        }
        System.out.println(maxByDrct + " by " + maxDrcts);
    }
    
    public void testLoadRaters() {
        String filename = "ratings.csv";
        ArrayList<Rater> raters = loadRaters(filename);
        // find the total number of raters
        System.out.println("the total number of raters: " + raters.size());
        // find each rater, their number of ratings, and each rating of them
        // for (Rater r : raters) {
        //     System.out.println("Rater " + r.getID() + " has rated " + r.numRatings() + " movies");
        //     ArrayList<String> items = r.getItemsRated();
        //     for (String item : items) {
        //         System.out.println(item + " " + r.getRating(item));
        //     }
        // }
        // find the number of ratings for a particular rater
        String rater_id = "193";
        for (Rater r : raters) {
            if (r.getID().equals(rater_id)) {
                System.out.println("Rater " + r.getID() + " has rated " + r.numRatings() + " movies");
            }
        }
        // find the maximum number of ratings by any rater and the name of the rater(s)
        // compute the number of ratings by each rater
        System.out.print("the maximum number of ratings by any rater: ");
        HashMap<Rater,Integer> ratingByRater = new HashMap<Rater,Integer>();
        for (Rater r : raters) {
            ratingByRater.put(r, r.numRatings());
        }
        // find the maximum number of ratings
        int maxByRater = 0;
        for (Rater r : ratingByRater.keySet()) {
            if (ratingByRater.get(r) > maxByRater) {
                maxByRater = ratingByRater.get(r);
            }
        }
        // find the rater(s) with the maximum number of ratings
        ArrayList<Rater> maxRaters = new ArrayList<Rater>();
        for (Rater r : ratingByRater.keySet()) {
            if (ratingByRater.get(r) == maxByRater) {
                maxRaters.add(r);
            }
        }
        System.out.println(maxByRater + " by " + maxRaters);
        // find the number of ratings a particular movie has
        String movie = "1798709";
        System.out.print("the  number of raters of movie " + movie + ": ");
        int numRater = 0;
        for (Rater r : raters) {
            if (r.hasRating(movie)) {
                numRater++;
            }
        }
        System.out.println(numRater);
        // find the number of movies rated by all these raters
        System.out.print("the  number of movie rated: ");
        ArrayList<String> movies = new ArrayList<String>();
        for (Rater r : raters) {
            ArrayList<String> moviesByEach = r.getItemsRated();
            for (String mov : moviesByEach) {
                if (!movies.contains(mov)) {
                    movies.add(mov);
                }
            }
        }
        System.out.println(movies.size());
    }
}
