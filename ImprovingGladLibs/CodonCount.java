import edu.duke.*;
import java.util.*;

public class CodonCount {
    private HashMap<String, Integer> codonMap;
    
    // constructor
    public CodonCount() {
        codonMap = new HashMap<String, Integer>();
    }
    
    private void buildCodonMap(int start, String dna) {
        codonMap.clear();
        while (true) {
            if (start > dna.length() - 3) {
                break;
            }
            String codon;
            if (start == dna.length() - 3) {
                codon = dna.substring(start);
            }
            else  {
                codon = dna.substring(start, start + 3);
            }
            if (codonMap.keySet().contains(codon)) {
                codonMap.put(codon, codonMap.get(codon) + 1);
            }
            else {
                codonMap.put(codon, 1);
            }
            start += 3;
        }
    }
    
    private String getMostCommonCodon() {
        String mostCommonCodon = "";
        int largestCount = 0;
        for (String s : codonMap.keySet()) {
            int currCount = codonMap.get(s);
            if (currCount > largestCount) {
                largestCount = currCount;
                mostCommonCodon = s;
            }
        }
        return mostCommonCodon;
    }
    
    private void printCodonCounts(int start, int end) {
        System.out.println("Codons with occurrences between " + start + 
                           " and " + end + ": ");
        for (String s : codonMap.keySet()) {
            int count = codonMap.get(s);
            if (count >= start && count <= end) {
                System.out.println(s + " " + count);
            }
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase();
        dna = dna.trim();
        for (int i = 0; i < 3; i++) {
            buildCodonMap(i, dna);
            System.out.println("number of unique codons: " + codonMap.size());
            String mostCommonCodon = getMostCommonCodon();
            System.out.println("most common codon: " + mostCommonCodon + 
                               " " + codonMap.get(mostCommonCodon));
            printCodonCounts(1, 5);
        }
    }
}
