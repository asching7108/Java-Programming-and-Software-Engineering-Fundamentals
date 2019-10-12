
/**
 * This is the assignment of finding a gene in DNA for Java 
 * programming: solving problems with software course.
 * @author Hsingyi Lin 
 * @version 08/29/2019
 */
import edu.duke.*;
import java.io.File;

public class Part1 {
    public int getIndexOfATG(String s) {
        return s.indexOf("ATG");
    }
    
    public int getIndexOfTAA(String s, int posATG) {
        return s.indexOf("TAA", posATG + 3);
    }
    
    public String findSimpleGene(String s) {
        // find the index of ATG
        int posATG = getIndexOfATG(s);
        // if ATG is not found, return the empty string
        if (posATG == -1) {
            return "";
        }
        // find the index of TAA after ATG
        int posTAA = getIndexOfTAA(s, posATG);
        // if TAA is not found, return the empty string
        if (posTAA == -1) {
            return "";
        }
        // if the length of the substring between ATG and 
        // TAA is not a multiple of 3, return the empty string
        if ((posTAA - posATG) % 3 != 0) {
            return "";
        }
        // else return the substring 
        else {
            return s.substring(posATG, posTAA + 3);
        }
    }
    
    public void testSimpleGene() {
        String s1 = "ACGTAATGG";
        String o1 = findSimpleGene(s1);
        System.out.println("Gene in " + s1 + " is: " + o1);
        String s2 = "ATAATGCGTATGG";
        String o2 = findSimpleGene(s2);
        System.out.println("Gene in " + s2 + " is: " + o2);
        String s3 = "ACGTATGG";
        String o3 = findSimpleGene(s3);
        System.out.println("Gene in " + s3 + " is: " + o3);
        String s4 = "ATAATGCGCCATTAATGG";
        String o4 = findSimpleGene(s4);
        System.out.println("Gene in " + s4 + " is: " + o4);
        String s5 = "ATAATGCGCCATAATGG";
        String o5 = findSimpleGene(s5);
        System.out.println("Gene in " + s5 + " is: " + o5);
    }
}
