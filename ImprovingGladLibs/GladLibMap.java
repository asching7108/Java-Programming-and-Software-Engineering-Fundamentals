import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> usedWordList;
    private ArrayList<String> usedCategoryList;
    private int numReplaced;
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        usedWordList = new ArrayList<String>();
        usedCategoryList = new ArrayList<String>();
        myRandom = new Random();
        numReplaced = 0;
    }
    
    public GladLibMap(String source){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        usedWordList = new ArrayList<String>();
        usedCategoryList = new ArrayList<String>();
        myRandom = new Random();
        numReplaced = 0;
    }
    
    private void initializeFromSource(String source) {
        String[] categories = new String[]{"adjective", "noun", "color", 
                                           "country", "name", "animal",
                                           "timeframe", "verb", "fruit"};
        for (String s : categories) {
            ArrayList<String> wordList= readIt(source+"/"+s+".txt"); 
            myMap.put(s, wordList);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        if (myMap.get(label) == null) {
            return "**UNKNOWN**";
        }
        return randomFrom(myMap.get(label));
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String label = w.substring(first+1,last);
        if (usedCategoryList.contains(label)) {
            usedCategoryList.add(label);
        }
        String sub;
        while (true) {
            sub = getSubstitute(label);
            if (!usedWordList.contains(sub)) {
                usedWordList.add(sub);
                numReplaced++;
                break;
            }
        }
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    private int totalWordsInMap() {
        int total = 0;
        for (String category : myMap.keySet()) {
            total += myMap.get(category).size();
        }
        return total;
    }
    
    private int totalWordsConsidered() {
        int total = 0;
        for (String category : usedCategoryList) {
            if (myMap.get(category) != null) {
                total += myMap.get(category).size();
            }
        }
        return total;
    }
    
    public void makeStory(){
        usedWordList.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\nNumber of words replaced: " + numReplaced);
        int totalWords = totalWordsInMap();
        System.out.println("the total number of words to pick from: " + totalWords);
        int totalWordsConsidered = totalWordsConsidered();
        System.out.println("the total number of words considered: " + totalWordsConsidered);        
    }
}
