
/**
 * This class tests CaesarCipher class.
 * @author Hsingyi Lin 
 * @version 09/03/2019
 */
import edu.duke.*;

public class TestCaesarCipherTwo {
    // helper method to find the numbers of each letter in the input string
    private int[] countLetters(String input) {
        int[] counts = new int[26];
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < input.length(); i++) {
            int index = alphabet.indexOf(Character.toLowerCase(input.charAt(i)));
            if (index != -1) {
                counts[index]++;
            }
        }
        return counts;
    }
    
    // helper method to find the index of the most frequent letter
    private int maxIndex(int[] values) {
        int largestValue = 0;
        int index = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > largestValue) {
                largestValue = values[i];
                index = i;
            }
        }
        return index;
    }

    // helper method to find the encrypt key by mapping the most
    // frequent letter to 'e'
    private int getKey(String s) {
        int [] counts = new int[26];
        counts = countLetters(s);
        int indexOfMax = maxIndex(counts);
        int key = (indexOfMax - 4 + 26) % 26;
        return key;
    }
    
    // helper method to return the half of the input string starting 
    // from the given start followed by every other letters
    private String halfOfString(String input, int start) {
        StringBuilder output = new StringBuilder("");
        for (int i = start; i < input.length(); i += 2) {
            output.append(input.charAt(i));
        }
        return output.toString();
    }

    // helper method to decrypt the input string by mapping the most
    // frequent letter to 'e' to determine the key used to encrypt,
    // and return the decrypted input string
    private String breakCaesarCipher (String input) {
        String h1 = halfOfString(input, 0);
        String h2 = halfOfString(input, 1);
        int key1 = getKey(h1);
        int key2 = getKey(h2);
        System.out.println("The first key is: " + key1);
        System.out.println("The second key is: " + key2);
        CaesarCipherTwo cc = new CaesarCipherTwo(key1, key2);
        String output = cc.decrypt(input);
        return output;
    }

    public void simpleTests() {
        FileResource fr = new FileResource();
        String s1 = fr.asString();
        CaesarCipherTwo cc1 = new CaesarCipherTwo(12, 2);
        String encrypted1 = cc1.encrypt(s1);
        System.out.println("Encrypted: " + encrypted1);
        String decrypted1 = breakCaesarCipher(encrypted1
        );
        System.out.println("Decrypted: " + decrypted1);
    }
}
