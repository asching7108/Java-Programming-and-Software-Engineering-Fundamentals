
/**
 * This is the assignment of finding a gene in DNA for Java 
 * programming: solving problems with software course.
 * @author Hsingyi Lin 
 * @version 08/29/2019
 */
import edu.duke.*;
import java.io.File;

public class Part4 {
    public void findLinks() {
        // get the URL
        URLResource ur = new URLResource(
        "http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        // for each word in the file at the URL
        for (String s : ur.words()) {
            // find youtube.com in the word
            String lowerS = s.toLowerCase();
            int index = lowerS.indexOf("youtube.com");
            // if youtube.com is not found, return directly
            if (index != -1) {
                // find the first " to the left of youtube.com
                int start = s.lastIndexOf("\"", index);
                // find the first " to the right of youtube.com
                int end = s.indexOf("\"", index);
                // print the substring between " and "
                System.out.println(s.substring(start + 1, end));
            }
        }
    }
}
