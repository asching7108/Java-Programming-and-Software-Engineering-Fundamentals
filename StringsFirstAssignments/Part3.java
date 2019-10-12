
/**
 * This is the assignment of finding a gene in DNA for Java 
 * programming: solving problems with software course.
 * @author Hsingyi Lin 
 * @version 08/29/2019
 */
import edu.duke.*;
import java.io.File;

public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb) {
        // find the first occurrence of stringa in stringb
        int first = stringb.indexOf(stringa);
        // if the first stringa is not found, return false
        if (first == -1) {
            return false;
        }
        // find the second occurrence of stringa in stringb
        int second = stringb.indexOf(stringa, first + stringa.length());
        // if the second stringa is not found, return false
        if (second == -1) {
            return false;
        }
        // return true as the two occurrences are found
        return true;
    }
    
    public String lastPart(String stringa, String stringb) {
        // find the first occurrence of stringa in stringb
        int first = stringb.indexOf(stringa);
        // if the first stringa is not found, return stringb
        if (first == -1) {
            return stringb;
        }
        // return the part of stringb that follows stringa
        return stringb.substring(first + stringa.length(), 
                                 stringb.length());
    }
    
    public void testing() {
        boolean t1 = twoOccurrences("by", "A story by Abby Long");
        System.out.println(t1);
        boolean t2 = twoOccurrences("a", "banana");
        System.out.println(t2);
        boolean t3 = twoOccurrences("atg", "ctgtatgta");
        System.out.println(t3);
        String o1 = lastPart("an", "banana");
        System.out.println(o1);
        String o2 = lastPart("zoo", "forest");
        System.out.println(o2);
        
    }
}
