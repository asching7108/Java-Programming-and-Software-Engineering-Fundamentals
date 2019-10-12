
/**
 * This is the assignment of finding many genes in DNA for  
 * Java programming: solving problems with software course.
 * @author Hsingyi Lin 
 * @version 08/29/2019
 */
import edu.duke.*;
import java.io.File;

public class Part2 {
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
        return (double) countCG / dna.length();
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
            startIndex += indexCTG - startIndex + 3;
        }
        return countCTG;
    }
    
    public void test() {
        double t1 = cgRatio("ATGCCATAG");
        System.out.println(t1);
        int t2 = countCTG("ATGCCTGCTGATAGCTGAA");
        System.out.println(t2);
    }
}
