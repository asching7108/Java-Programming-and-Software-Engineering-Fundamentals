
/**
 * This is the assignment of finding many genes in DNA for  
 * Java programming: solving problems with software course.
 * @author Hsingyi Lin 
 * @version 08/29/2019
 */
import edu.duke.*;
import java.io.File;

public class Part3 {
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

    public double cgRatio(String dna) {
        int countCG = 0;
        int startIndex = 0;
        while (true) {
            int indexCG;
            int indexC = dna.indexOf("C", startIndex);
            int indexG = dna.indexOf("G", startIndex);
            if (indexC == -1 ||
                (indexG != -1 && indexG < indexC)) {
                indexCG = indexG;
            }
            else {
                indexCG = indexC;
            }
            if (indexCG == -1) {
                break;
            }
            countCG++;
            startIndex += indexCG - startIndex + 1;
        }
        return ((double) countCG) / dna.length();
    }

    public int countCTG(String dna) {
        int countCTG = 0;
        int startIndex = 0;
        while (true) {
            int indexCTG = dna.indexOf("CTG", startIndex);
            if (indexCTG == -1) {
                break;
            }
            countCTG++;
            startIndex = indexCTG + 3;
        }
        return countCTG;
    }
    
    public void processGenes(StorageResource sr) {
        // find the number of genes
        int count = 0;
        for (String s : sr.data()) {
            count++;
        }
        System.out.println("number of genes: " 
                           + count);
        // find all the Strings in sr that are longer than 9 characters
        int countChars = 0;
        for (String s : sr.data()) {
            if (s.length() > 60) {
                System.out.println(s);
                countChars++;
            }
        }
        System.out.println("number of Strings longer than 60 characters: " 
                           + countChars);
        // find all the Strings in sr whose CGratio is higher than 0.35
        int count35ratio = 0;
        for (String s : sr.data()) {
            double ratio = cgRatio(s);
            if (cgRatio(s) > 0.35) {
                System.out.println(s);
                count35ratio++;
            }
        }
        System.out.println("number of Strings CGratio higher than 0.35: " 
                           + count35ratio);
        // find the length of the longest gene in sr
        int longestLen = 0;
        for (String s : sr.data()) {
            if (s.length() > longestLen) {
                longestLen = s.length();
            }
        }
        System.out.println("longest length: " + longestLen);
    }
    
    public void testProcessGenes() {
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString().toUpperCase();
        StorageResource sr = getAllGenes(dna);
        processGenes(sr);
        int countCTGs = countCTG(dna);
        System.out.println("the codon CTG appears: " + countCTGs + " times");
    }
}
