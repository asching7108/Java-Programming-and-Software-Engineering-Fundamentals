
/**
 * This is the assignment of finding many genes in DNA for  
 * Java programming: solving problems with software course.
 * @author Hsingyi Lin 
 * @version 08/29/2019
 */
import edu.duke.*;
import java.io.File;

public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        // find the first stopIndex
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (true) {
            // if stopIndex is not found, return -1
            if (stopIndex == -1) {
                return stopIndex;
            }
            // if the length of the substring between the startIndex
            // and the stopIndex is a multiple of 3, return stopIndex
            if ((stopIndex - startIndex) % 3 == 0) {
                return stopIndex;
            }
            // if the length is not a multiple of 3, find the next
            // stopIndex
            stopIndex = dna.indexOf(stopCodon, stopIndex + 1);
        }
    }
    
    public String findGene(String dna) {
        // find the startIndex
        int startIndex = dna.indexOf("ATG");
        // if the startIndex is not found, return the empty string
        if (startIndex == -1) {
            return "";
        }
        // find the stopIndexes of different stop codons
        int indexTAA = findStopCodon(dna, startIndex, "TAA");
        int indexTAG = findStopCodon(dna, startIndex, "TAG");
        int indexTGA = findStopCodon(dna, startIndex, "TGA");
        // find the minimal stopIndex
        int minIndex;
        if (indexTAA == -1 || 
            (indexTAG != -1 && indexTAG < indexTAA)) {
            minIndex = indexTAG;
        }
        else {
            minIndex = indexTAA;
        }
        if (minIndex == -1 ||
            (indexTGA != -1 && indexTGA < minIndex)) {
            minIndex = indexTGA;
        }
        // if the minIndex is not found, return the empty string
        if (minIndex == -1) {
            return "";
        }
        // return the substring
        return dna.substring(startIndex, minIndex + 3);
    }
    
    public void testFindStopCodon() {
        String s1 = "xxxxxxxATGxxxxxxTAAxxx";
        int o1 = findStopCodon(s1, 7, "TAA");
        if (o1 != -1) {
            System.out.println("correct");
        }
        String s2 = "xxxxxxxATGxxxxxTAAxxxxTAAxxx";
        int o2 = findStopCodon(s2, 7, "TAA");
        if (o2 != -1) {
            System.out.println("correct");
        }
        String s3 = "xxxxxxxATGxxxxxTAAxxx";
        int o3 = findStopCodon(s3, 7, "TAA");
        if (o3 == -1) {
            System.out.println("correct");
        }
        String s4 = "xxxxxxxATGxxxxxTAxxx";
        int o4 = findStopCodon(s4, 7, "TAA");
        if (o4 == -1) {
            System.out.println("correct");
        }
    }
    
    public void testFindGene() {
        String s1 = "xxxxxxxxxxxxxTAAxxx";
        String o1 = findGene(s1);
        System.out.println(s1 + "has gene: " + o1);
        String s2 = "xxxxATGxxxxxTAGxxxxTAAxxxxTGAxxx";
        String o2 = findGene(s2);
        System.out.println(s2 + "has gene: " + o2);
        String s3 = "xxxxATGxxxxxxTAGxxxTAAxxxTGAxxx";
        String o3 = findGene(s3);
        System.out.println(s3 + "has gene: " + o3);
        String s4 = "xxxxATGxxxxxTAGxxxTAAxxxTGAxxx";
        String o4 = findGene(s4);
        System.out.println(s4 + "has gene: " + o4);
        String s5 = "xxxxATGxxxxxTAGxxxTAAxxxxTGAxxx";
        String o5 = findGene(s5);
        System.out.println(s5 + "has gene: " + o5);
        printAllGenes("xxxxATGxxxxxTAGxxxxTAAxxxxTGAxxxATGxxxxxxTAGxxxTAAxxxTGAxxxATGxxxxxTAGxxxTAAxxxxTGAxxx");
    }
    
    public void printAllGenes(String dna) {
        while (true) {
            String gene = findGene(dna);
            // if gene is not found, break the loop
            if (gene.isEmpty()) {
                break;
            }
            System.out.println(gene);
            dna = dna.substring(dna.indexOf(gene) + gene.length(),
                                dna.length());
        }
    }
    
    public StorageResource getAllGenes(String dna) {
        StorageResource sr = new StorageResource();
        while (true) {
            String gene = findGene(dna);
            // if gene is not found, break the loop
            if (gene.isEmpty()) {
                break;
            }
            sr.add(gene);
            dna = dna.substring(dna.indexOf(gene) + gene.length(),
                                dna.length());
        }
        return sr;
    }
    
    public void testGetAllGenes() {
        StorageResource sr = getAllGenes("xxxxATGxxxxxTAGxxxxTAAxxxxTGAxxxATGxxxxxxTAGxxxTAAxxxTGAxxxATGxxxxxTAGxxxTAAxxxxTGAxxx");
        for (String s : sr.data()) {
            System.out.println(s);
        }
    }
}
