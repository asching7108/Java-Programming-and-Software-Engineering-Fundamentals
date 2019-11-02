import java.util.*;
import java.io.File;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        String output = "";
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            output += message.charAt(i);
        }
        return output;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i++) {
            String slice = sliceString(encrypted, i, klength);
            key[i] = cc.getKey(slice);
        }
        return key;
    }

    public void breakVigenere () {
        FileResource fr1 = new FileResource();
        String encrypted = fr1.asString();
        HashMap<String, HashSet<String>> languages = 
            new HashMap<String, HashSet<String>>();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr2 = new FileResource(f);
            System.out.println("start reading " + f.getName());
            HashSet<String> dictionary = readDictionary(fr2);
            System.out.println("finish reading " + f.getName());
            languages.put(f.getName(), dictionary);
        }
            String decrypted = breakForAllLangs(encrypted, languages);
            System.out.println(decrypted);
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dictionary = new HashSet<String>();
        for (String word : fr.lines()) {
            word = word.toLowerCase();
            dictionary.add(word);
        }
        return dictionary;
    }
    
    public int countWords(String message, HashSet<String> dictionary) {
        int count = 0;
        for (String word : message.split("\\W+")) {
            if (dictionary.contains(word.toLowerCase())) {
                count++;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        String output = "";
        int mostWordCounts = 0;
        int[] rightKeys = null;
        char mostCommonChar = mostCommonCharIn(dictionary);
        for (int i = 1; i <= 100; i++) {
            VigenereBreaker vb = new VigenereBreaker();
            int[] keys = vb.tryKeyLength(encrypted, i, mostCommonChar);
            VigenereCipher vc = new VigenereCipher(keys);
            String decrypted = vc.decrypt(encrypted);
            int wordCounts = countWords(decrypted, dictionary);
            if (wordCounts > mostWordCounts) {
                mostWordCounts = wordCounts;
                output = decrypted;
                rightKeys = keys;
            }
        }
        System.out.println("the key length: " + rightKeys.length);
        System.out.println("the number of valid words: " + mostWordCounts);
        return output;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
        // count the number of each characters
        HashMap<Character, Integer> charCounts = 
            new HashMap<Character, Integer>();
        for (String word : dictionary) {
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (!charCounts.containsKey(ch)) {
                    charCounts.put(ch, 1);
                }
                else {
                    charCounts.put(ch, charCounts.get(ch) + 1);
                }
            }
        }
        // find the character with the largest count
        char mostCommonChar = ' ';
        int largestCount = 0;
        for (char ch : charCounts.keySet()) {
            int count = charCounts.get(ch);
            if (count > largestCount) {
                largestCount = count;
                mostCommonChar = ch;
            }
        }
        return mostCommonChar;
    }
    
    public String breakForAllLangs(String encrypted, 
        HashMap<String, HashSet<String>> languages) {
        String decrypted = "";
        String rightLang = "";
        int mostWordCount = 0;
        for (String langName : languages.keySet()) {
            HashSet<String> dictionary = languages.get(langName);
            System.out.println("break in " + langName + ": ");
            String trial = breakForLanguage(encrypted, dictionary);
            int wordCount = countWords(trial, dictionary);
            if (wordCount > mostWordCount) {
                mostWordCount = wordCount;
                rightLang = langName;
                decrypted = trial;
            }
        }
        System.out.println("the original language: " + rightLang);
        System.out.println("the valid word count: " + mostWordCount);
        return decrypted;
    }
}
