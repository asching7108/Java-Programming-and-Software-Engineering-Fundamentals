
/**
 * This class determines the word that occurs the most often in a file.
 * @author Hsingyi Lin
 * @version 09/03/2019
 */
import edu.duke.*;
import java.util.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    // constructor
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    // helper method to select a file, put the unique words found into 
    // myWords and put the count of occurances of that word into myFreqs
    private void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for (String s : fr.words()) {
            s = s.toLowerCase();
            if (myWords.indexOf(s) == -1) {
                myWords.add(s);
                myFreqs.add(1);
            }
            else {
                int index = myWords.indexOf(s);
                int count = myFreqs.get(index);
                myFreqs.set(index, count + 1);
            }
        }
    }
    
    private int findIndexOfMax() {
        int indexOfMax = -1;
        int countOfMax = 0;
        for (int i = 0; i < myWords.size(); i++) {
            int count = myFreqs.get(i);
            if (count > countOfMax) {
                indexOfMax = i;
                countOfMax = count;
            }
        }
        return indexOfMax;
    }
    
    public void tester() {
        findUnique();
        System.out.println("Number of unique words: " + myWords.size());
        for (int i = 0; i < myWords.size(); i++) {
            // System.out.println(myFreqs.get(i) + " " + myWords.get(i));
        }
        int indexOfMax = findIndexOfMax();
        System.out.println("The word that occurs most often and its count " + 
                           "are: " + myWords.get(indexOfMax) + " " + 
                           myFreqs.get(indexOfMax));
    }
}
