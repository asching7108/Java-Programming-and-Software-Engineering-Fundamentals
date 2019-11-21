
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private int num;
    private HashMap<String, ArrayList<String>> followMap;
    
    public EfficientMarkovModel(int model) {
        myRandom = new Random();
        num = model;
        followMap = new HashMap<String, ArrayList<String>>();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
        buildMap();
    }

    public void buildMap() {
        for (int i = 0; i < myText.length() - num; i++) {
            String key = myText.substring(i, i + num);
            ArrayList<String> follows;
            if (!followMap.containsKey(key)) {
                follows = new ArrayList<String>();
            }
            else {
                follows = followMap.get(key);
            }
            follows.add(myText.substring(i + num, i + num + 1));
            followMap.put(key, follows);
        }
        String key = myText.substring(myText.length() - num);
        if (!followMap.containsKey(key)) {
            ArrayList<String> follows = new ArrayList<String>();
            followMap.put(key, follows);
        }
    }
    
    public ArrayList<String> getFollows(String key) {
        if (!followMap.containsKey(key)) {
            return new ArrayList<String>();
        }
        return followMap.get(key);
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - num);
        String key = myText.substring(index, index + num);
        sb.append(key);
        
        for(int k=0; k < numChars - num; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.isEmpty()) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        printHashMapInfo();
        return sb.toString();
    }
    
    public void printHashMapInfo() {
        //System.out.println("HashMap " + followMap);
        System.out.println("number of keys " + followMap.size());
        ArrayList<String> largest = new ArrayList<String>();
        for (String key : followMap.keySet()) {
            if (followMap.get(key).size() > largest.size()) {
                largest = followMap.get(key);
            }
        }
        System.out.println("size of largest value " + largest.size());
        ArrayList<String> largestKeys = new ArrayList<String>();
        for (String key : followMap.keySet()) {
            if (followMap.get(key).size() == largest.size()) {
                largestKeys.add(key);
            }
        }        
        System.out.println("keys with maximum size " + largestKeys);
    }
    
    public String toString() {
        return "EfficientMarkovModel of order " + num;
    }
    
}
