
/**
 * This program finds the most common word length of words from a file.
 * @author Hsingyi Lin 
 * @version 09/02/2019
 */
import edu.duke.*;

public class WordLengths {
    public int[] countWordLengths(FileResource resource, int[] counts) {
        for(String s: resource.words()) {
            int len = s.length();
            if (!Character.isLetter(s.charAt(len - 1))) {
                len--;
            }
            if (!Character.isLetter(s.charAt(0)) && len > 1) {
                len--;
            }
            if (len < counts.length) {
                counts[len]++;
            }
            else {
                counts[counts.length - 1]++;
            }
        }
        return counts;
    }
    
    public int indexOfMax(int[] values) {
        int largestValue = 0;
        int index = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > largestValue) {
                largestValue = values[i];
                index = i;
            }
        }
        return index;
    }
    
    public void testCountWordLengths() {
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        counts = countWordLengths(fr, counts);
        int mostCommonLen = indexOfMax(counts);
        int mostCommonLenCount = counts[mostCommonLen];
        System.out.println(mostCommonLen + ": " + mostCommonLenCount);
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0) {
                System.out.println(i + ": " + counts[i]);
            }
        }
    }
}
