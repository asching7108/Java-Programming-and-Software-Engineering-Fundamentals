
/**
 * This program transform words from a file into another form.
 * @author Hsingyi Lin
 * @version 09/01/2019
 */
import edu.duke.*;

public class WordPlay {
    public boolean isVowel(char ch) {
        ch = Character.toUpperCase(ch);
        if (ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U') {
            return true;
        }
        return false;
    }
    
    public String replaceVowels(String phrase, char ch) {
        StringBuilder output = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++) {
            char currCh = phrase.charAt(i);
            if (isVowel(currCh)) {
                output.setCharAt(i, ch);
            }
            else {
                output.setCharAt(i, currCh);
            }
        }
        return output.toString();
    }
    
    public String emphasize(String phrase, char ch) {
        ch = Character.toUpperCase(ch);
        StringBuilder output = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++) {
            char currCh = phrase.charAt(i);
            if (Character.toUpperCase(currCh) == ch) {
                if (i % 2 == 0) {
                    output.setCharAt(i, '*');
                }
                else {
                    output.setCharAt(i, '+');
                }
            }
            else {
                output.setCharAt(i, currCh);
            }
        }
        return output.toString();
    }
    
    public void testWordPlay() {
        String t1 = replaceVowels("Oh Hello World", '*');
        System.out.println(t1);
        String t2 = emphasize("Mary Bella Abracadabra", 'a');
        System.out.println(t2);
    }
}
