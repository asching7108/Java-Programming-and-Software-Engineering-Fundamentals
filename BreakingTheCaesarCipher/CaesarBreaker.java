
/**
 * This program decrypts a message that was encrypted with one key
 * or two keys.
 * @author Hsingyi Lin 
 * @version 09/02/2019
 */
import edu.duke.*;

public class CaesarBreaker {
    public int[] countLetters(String input) {
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
    
    public int maxIndex(int[] values) {
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
    
    public String decrypt(String input) {
        int key = getKey(input);
        CaesarCipher cc = new CaesarCipher();
        String message = cc.encrypt(input, 26 - key);
        return message;
    }
    
    public String halfOfString(String message, int start) {
        StringBuilder newString = new StringBuilder("");
        for (int i = start; i < message.length(); i += 2) {
            newString.append(message.charAt(i));
        }
        return newString.toString();
    }
    
    public int getKey(String s) {
        int [] counts = new int[26];
        counts = countLetters(s);
        int indexOfMax = maxIndex(counts);
        int key = (indexOfMax - 4 + 26) % 26;
        return key;
    }
    
    public String decryptTwoKeys(String encrypted) {
        String s1 = halfOfString(encrypted, 0);
        String s2 = halfOfString(encrypted, 1);
        int key1 = getKey(s1);
        int key2 = getKey(s2);
        System.out.println("The first key is: " + key1);
        System.out.println("The second key is: " + key2);
        CaesarCipher cc = new CaesarCipher();
        String d1 = cc.encrypt(s1, 26 - key1);
        String d2 = cc.encrypt(s2, 26 - key2);
        StringBuilder decrypted = new StringBuilder("");
        for (int i = 0; i < encrypted.length(); i++) {
            if (i % 2 == 0) {
                decrypted.append(d1.charAt(i / 2));
            }
            else {
                decrypted.append(d2.charAt(i / 2));
            }
        }
        return decrypted.toString();
    }
    
    public void testDecrypt() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key = 23;
        CaesarCipher cc = new CaesarCipher();
        String encrypted = cc.encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
        String decrypted = decrypt(encrypted);
        System.out.println(decrypted);
    }

    public void testDecryptTwoKeys() {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        System.out.println("encrypted: " + encrypted);
        String decrypted = decryptTwoKeys(encrypted);
        System.out.println("decrypted: " + decrypted);
        String t1 = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        System.out.println("encrypted: " + t1);
        String decryptedt1 = decryptTwoKeys(t1);
        System.out.println("decrypted: " + decryptedt1);
        CaesarCipher cc = new CaesarCipher();
        String t2 = "Top ncmy qkff vi vguv vbg ycpx";
        System.out.println("encrypted: " + t2);
        String decryptedt2 = cc.encryptTwoKeys(t2, 24, 6);
        System.out.println("decrypted: " + decryptedt2);
    }
}
