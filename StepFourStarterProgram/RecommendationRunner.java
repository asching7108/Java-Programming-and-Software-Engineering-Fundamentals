
/**
 * Allows the course website to run the recommender program.
 * 
 * @author Hsingyi Lin 
 * @version 10/11/2019
 */

import java.util.*;

public class RecommendationRunner implements Recommender {
    private static String moviefile = "ratedmoviesfull.csv";
    private static String ratingsfile = "ratings.csv";

    private FourthRatings getRatings() {
        MovieDatabase.initialize(moviefile);
        RaterDatabase.initialize(ratingsfile);
        FourthRatings ratings = new FourthRatings();
        return ratings;
    }
    
    public ArrayList<String> getItemsToRate () {
        FourthRatings ratings = getRatings();
        ArrayList<Rating> avgRatings = ratings.getAverageRatings(5);
        ArrayList<String> top20 = new ArrayList<String>();
        for (int i = 0; i < Math.min(20, avgRatings.size()); i++) {
            Rating rating = avgRatings.get(i);
            top20.add(rating.getItem());
        }
        return top20;
    }
    
    public void printRecommendationsFor (String webRaterID) {
        FourthRatings ratings = getRatings();
        ArrayList<Rating> avgRatings = ratings.getSimilarRatings(webRaterID, 20, 5);
        // print a message if no match is found
        if (avgRatings.isEmpty()) {
            System.out.println("Sorry, we don't have recommendation for you just yet.");
            return;
        }
        // set the style of the table
        System.out.println("<style>\n\ttable {");
        System.out.println("\t\tbackground-color: GhostWhite;");
        System.out.println("\t\tborder-style: solid;");
        System.out.println("\t\tborder-color: RoyalBlue;");
        System.out.println("\t\tborder-width: 1px;");
        System.out.println("\t\twidth: 60%;");
        System.out.println("\t\tmargin: auto;");
        System.out.println("\t\tpadding: 2px 9px 2px 2px;");
        System.out.println("\t\tcolor: RoyalBlue;");
        System.out.println("\t}\n</style>");
        // print a table of recommended movies
        System.out.println("<table>\n\t<tr>\n\t\t<th>Title</th>\n\t\t<th>Year</th>\n\t\t<th>Director</th>\n\t\t<th>Genre</th>\n\t\t<th>Minutes</th>\n\t\t<th>Rating</th>\n\t</tr>");
        for (int i = 0; i < Math.min(20, avgRatings.size()); i++) {
            Rating rating = avgRatings.get(i);
            System.out.println("\t<tr>");
            System.out.println("\t\t<td>" + MovieDatabase.getTitle(rating.getItem()) + "</td>");
            System.out.println("\t\t<td>" + MovieDatabase.getYear(rating.getItem()) + "</td>");
            System.out.println("\t\t<td>" + MovieDatabase.getDirector(rating.getItem()) + "</td>");
            System.out.println("\t\t<td>" + MovieDatabase.getGenres(rating.getItem()) + "</td>");
            System.out.println("\t\t<td>" + MovieDatabase.getMinutes(rating.getItem()) + "</td>");
            System.out.println("\t\t<th>" + Math.round(rating.getValue()) + "</th>");
            System.out.println("\t</tr>");
        }
        System.out.println("</table>");
    }
}
