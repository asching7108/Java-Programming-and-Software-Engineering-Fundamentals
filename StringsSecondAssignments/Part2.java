
/**
 * This is the assignment of finding a gene in DNA for Java 
 * programming: solving problems with software course.
 * @author Hsingyi Lin 
 * @version 08/29/2019
 */
import edu.duke.*;
import java.io.File;

public class Part2 {
    public int howMany(String stringa, String stringb) {
        int occur = 0;
        int startIndex = 0;
        while (true) {
            // find the index of stringa in stringb
            int index = stringb.indexOf(stringa, startIndex);
            // if stringa is not found, return the occurrence
            if (index == -1) {
                return occur;
            }
            // update occurrence
            occur++;
            // update the startIndex
            startIndex += index - startIndex + stringa.length();
        }
    }
    
    public void testHowMany() {
        int t1 = howMany("GAA", "ATGAACGAATTGAATC");
        System.out.println(t1);
        int t2 = howMany("AA", "ATAAAA");
        System.out.println(t2);
    }
}
