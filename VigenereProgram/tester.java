import java.util.*;
import edu.duke.*;

public class tester {
    public void testSliceString() {
        VigenereBreaker vb = new VigenereBreaker();
        String t1 = vb.sliceString("abcdefghijklm", 0, 3);
        System.out.println(t1);
    }
    
    public void testTryKeyLength() {
        FileResource fr = new FileResource();
        String input = fr.asString();
        VigenereBreaker vb = new VigenereBreaker();
        int[] keys = vb.tryKeyLength(input, 38, 'e');
        for (int i : keys) {
            System.out.print(i + " ");
        }
        System.out.println();
        FileResource fr2 = new FileResource();
        HashSet<String> dictionary = vb.readDictionary(fr2);
        int count = vb.countWords(input, dictionary);
        System.out.println("countWords: " + count);
    }
}
