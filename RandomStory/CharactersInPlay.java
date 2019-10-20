
/**
 * This class finds  the characters in one of Shakespeare’s plays that 
 * have the most speaking parts. 
 * @author Hsingyi Lin
 * @version 09/03/2019
 */
import edu.duke.*;
import java.util.*;

public class CharactersInPlay {
    private ArrayList<String> characters;
    private ArrayList<Integer> charFreqs;
    // constructor
    public CharactersInPlay() {
        characters = new ArrayList<String>();
        charFreqs = new ArrayList<Integer>();
    }
    
    private void update(String person) {
        int index = characters.indexOf(person);
        if (index == -1) {
            characters.add(person);
            charFreqs.add(1);
        }
        else {
            int count = charFreqs.get(index);
            charFreqs.set(index, count + 1);
        }
    }
    
    private void findAllCharacters() {
        characters.clear();
        charFreqs.clear();
        FileResource fr = new FileResource();
        for (String s : fr.lines()) {
            s = s.toUpperCase();
            int indexOfPeriod = s.indexOf(".");
            if (indexOfPeriod != -1) {
                String person = s.substring(0, indexOfPeriod);
                update(person);
            }
        }
    }    
    
    private int findIndexOfMax() {
        int indexOfMax = -1;
        int countOfMax = 0;
        for (int i = 0; i < characters.size(); i++) {
            int count = charFreqs.get(i);
            if (count > countOfMax) {
                indexOfMax = i;
                countOfMax = count;
            }
        }
        return indexOfMax;
    }

    private void charactersWithNumParts(int num1, int num2) {
        for (int i = 0; i < characters.size(); i++) {
            int freq = charFreqs.get(i);
            if (freq >= num1 && freq <= num2) {
                System.out.println(characters.get(i) + " " + charFreqs.get(i));
            }
        }
    }
    
    public void tester() {
        findAllCharacters();
        for (int i = 0; i < characters.size(); i++) {
            if (charFreqs.get(i) > 10) {
                System.out.println(characters.get(i) + " " + charFreqs.get(i));
            }
        }
        int indexOfMax = findIndexOfMax();
        System.out.println("The character with the most speaking pparts is: " + 
                           characters.get(indexOfMax) + " " + 
                           charFreqs.get(indexOfMax));
        int num1 = 10;
        int num2 = 200;
        System.out.println("The characters have speaking parts between " + 
                           num1 + " and " + num2 + " are: ");
        charactersWithNumParts(num1, num2);
    }
}
