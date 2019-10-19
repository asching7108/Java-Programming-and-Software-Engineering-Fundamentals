
/**
 * This class encrypts and decrypts Strings using Caesar Cipher 
 * with the given key.
 * @author Hsingyi Lin 
 * @version 09/03/2019
 */

public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    // constructor
    public CaesarCipherTwo(int key1, int key2) {
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet1 = alphabet.substring(key1) + 
                          alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + 
                          alphabet.substring(0, key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    // encrypt the input string using shiftedAlphabet
    public String encrypt(String input) {
        StringBuilder output = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            int index = alphabet.indexOf(Character.toLowerCase(ch));
            if (index == -1) {
                output.setCharAt(i, ch);
            }
            else if (i % 2 == 0) {
                if (Character.isLowerCase(ch)) {
                    output.setCharAt(i, shiftedAlphabet1.charAt(index));
                }
                else {
                    output.setCharAt(i, Character.toUpperCase(shiftedAlphabet1.charAt(index)));
                }
            }
            else {
                if (Character.isLowerCase(ch)) {
                    output.setCharAt(i, shiftedAlphabet2.charAt(index));
                }
                else {
                    output.setCharAt(i, Character.toUpperCase(shiftedAlphabet2.charAt(index)));
                }
            }
        }
        return output.toString();
    }
    
    // decrypt the input string
    public String decrypt(String input) {
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        String output = cc.encrypt(input);
        return output;
    }

}
