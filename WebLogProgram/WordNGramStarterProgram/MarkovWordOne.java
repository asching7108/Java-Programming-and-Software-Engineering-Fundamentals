
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);
            //System.out.println(key + " size " + follows.size() + " " + follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = next;
        }
        
        return sb.toString().trim();
    }
    
    private int indexOf(String[] words, String target, int start) {
        for (int i = start; i < words.length; i++) {
            if (words[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }
    
    private ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int start = 0;
        while (true) {
            int index = indexOf(myText, key, start);
            if (index == -1) {
                break;
            }
            if (index < myText.length - 1) {
                follows.add(myText[index + 1]);
                start = index + 1;
            }
        }
        return follows;
    }

    public void testIndexOf() {
        String text = "this is just a test yes this is a simple test";
        String test[] = text.split("\\s+");
        System.out.println(indexOf(test, "this", 0));
        System.out.println(indexOf(test, "this", 3));
        System.out.println(indexOf(test, "frog", 0));
        System.out.println(indexOf(test, "frog", 5));
        System.out.println(indexOf(test, "simple", 2));
        System.out.println(indexOf(test, "test", 5));
    }
}
