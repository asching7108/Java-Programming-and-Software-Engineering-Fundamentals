
/**
 * This program encrypts phrases using the Carsar Cipher.
 * @author Hsingyi Lin
 * @version 09/01/2019
 */
import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        StringBuilder output = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String newAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        for (int i = 0; i < input.length(); i++) {
            char currCh = input.charAt(i);
            char currChUC = Character.toUpperCase(currCh);
            if (alphabet.indexOf(currChUC) != -1) {
                if (Character.isUpperCase(currCh)) {
                    output.setCharAt(i, newAlphabet.charAt(alphabet.indexOf(currChUC)));
                }
                else {
                    output.setCharAt(i, Character.toLowerCase(newAlphabet.charAt(alphabet.indexOf(currChUC))));
                }
            }
            else {
                output.setCharAt(i, currCh);
            }
        }
        return output.toString();
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder output = new StringBuilder("");
        for (int i = 0; i < input.length(); i++) {
            char currCh = input.charAt(i);
            String ch;
            if (i % 2 == 0) {
                ch = encrypt(Character.toString(currCh), key1);
            }
            else {
                ch = encrypt(Character.toString(currCh), key2);
            }
            output.append(ch);
        }
        return output.toString();
    }
    
    public void testEncrypt() {
        String t1 = encrypt("FIRST LEGION ATTACK EAST FLANK!", 23);
        System.out.println(t1);
        String t2 = encrypt("First Legion", 17);
        System.out.println(t2);
        String t3 = encryptTwoKeys("First Legion", 23, 17);
        System.out.println(t3);
        String t4 = encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15);
        System.out.println(t4);
        String t5 = encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21);
        System.out.println(t5);
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key = 23;
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
}
