
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text) {
        myText = text.split("\\s+");
    }
    
    private int indexOf(String[] words, WordGram target, int start) {
        for (int i = start; i < words.length - target.length(); i++) {
            WordGram input = new WordGram(words, i, target.length());
            if (input.equals(target)) {
                return i;
            }
        }
        return -1;
    }

    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int start = 0;
        while (true) {
            int index = indexOf(myText, kGram, start);
            if (index == -1) {
                break;
            }
            if (index < myText.length - kGram.length()) {
                follows.add(myText[index + kGram.length()]);
                start = index + 1;
            }
        }
        return follows;
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - myOrder);  // random word to start with
        WordGram kGram = new WordGram(myText, index, myOrder);
        sb.append(kGram.toString());
        sb.append(" ");
        for(int i = 0; i < numWords - myOrder; i++){
            ArrayList<String> follows = getFollows(kGram);
            //System.out.println(kGram + " size " + follows.size() + " " + follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            kGram = kGram.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
}
