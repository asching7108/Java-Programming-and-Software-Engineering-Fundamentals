
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class EfficientMarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    HashMap<WordGram, ArrayList<String>> followMap;
    
    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
        followMap = new HashMap<WordGram, ArrayList<String>>();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text) {
        myText = text.split("\\s+");
        buildMap();
        printHashMapInfo();
    }
        
    private void buildMap() {
        for (int i = 0; i < myText.length - myOrder; i++) {
            WordGram kGram = new WordGram(myText, i, myOrder);
            ArrayList<String> follows;
            if (!followMap.containsKey(kGram)) {
                follows = new ArrayList<String>();
            }
            else {
                follows = followMap.get(kGram);
            }
            follows.add(myText[i + myOrder]);
            followMap.put(kGram, follows);
        }
        WordGram kGram = new WordGram(myText, myText.length - myOrder, myOrder);
        if (!followMap.containsKey(kGram)) {
            ArrayList<String> follows = new ArrayList<String>();
            followMap.put(kGram, follows);
        }
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
        if (!followMap.containsKey(kGram)) {
            return new ArrayList<String>();
        }
        return followMap.get(kGram);
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
    
    public void printHashMapInfo() {
        //System.out.println("HashMap " + followMap);
        System.out.println("number of keys " + followMap.size());
        ArrayList<String> largest = new ArrayList<String>();
        for (WordGram key : followMap.keySet()) {
            if (followMap.get(key).size() > largest.size()) {
                largest = followMap.get(key);
            }
        }
        System.out.println("size of largest value " + largest.size());
        ArrayList<WordGram> largestKeys = new ArrayList<WordGram>();
        for (WordGram key : followMap.keySet()) {
            if (followMap.get(key).size() == largest.size()) {
                largestKeys.add(key);
            }
        }        
        System.out.println("keys with maximum size " + largestKeys);
    }
}
