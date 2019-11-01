import edu.duke.*;
import java.util.*;
import java.io.File;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordMap;
    
    public WordsInFiles() {
        wordMap = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        for (String s : fr.words()) {
            if (wordMap.keySet().contains(s)) {
                ArrayList<String> filenames = wordMap.get(s);
                if (filenames.indexOf(f.getName()) == -1) {
                    filenames.add(f.getName());
                    wordMap.put(s, filenames);
                }
            }
            else {
                ArrayList<String> filenames = new ArrayList<String>();
                filenames.add(f.getName());
                wordMap.put(s, filenames);
            }
        }
    }
    
    private void buildWordFileMap() {
        wordMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    
    private int maxNumber() {
        int max = 0;
        for (String word : wordMap.keySet()) {
            ArrayList<String> filenames = wordMap.get(word);
            int count = filenames.size();
            if (count > max) {
                max = count;
            }
        }
        return max;
    }
    
    private ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> words = new ArrayList<String>();
        for (String word : wordMap.keySet()) {
            ArrayList<String> filenames = wordMap.get(word);
            if (filenames.size() == number) {
                words.add(word);
            }
        }
        return words;
    }
    
    private void printFilesIn(String word) {
        ArrayList<String> filenames = wordMap.get(word);
        System.out.println(word + " appears in: ");
        for (String filename : filenames) {
            System.out.println(filename);
        }
    }
    
    public void tester() {
        buildWordFileMap();
        int max = maxNumber();
        System.out.println("the maximum number of files any word appears in: " + max);
        for (int i = 1; i <= max; i++) {
            ArrayList<String> numWords = wordsInNumFiles(i);            
            System.out.println("the number of words occuring in " + i + 
                               "files: " + numWords.size());
        }
        ArrayList<String> maxNumWords = wordsInNumFiles(max);
        for (String word : maxNumWords) {
            // printFilesIn(word);
        }
        String word = "tree";
        System.out.println(word + " appears in: ");
        ArrayList<String> list = wordMap.get(word);
        for (String filename : list) {
            System.out.println(filename);
        }
        
    }
}
