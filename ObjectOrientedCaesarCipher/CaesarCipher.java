
/**
 * This class encrypts and decrypts Strings using Caesar Cipher 
 * with the given key.
 * @author Hsingyi Lin 
 * @version 09/03/2019
 */

public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    // constructor
    public CaesarCipher(int key) {
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet = alphabet.substring(key) + 
                          alphabet.substring(0, key);
        mainKey = key;
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
            else if (Character.isLowerCase(ch)) {
                output.setCharAt(i, shiftedAlphabet.charAt(index));
            }
            else {
                output.setCharAt(i, Character.toUpperCase(shiftedAlphabet.charAt(index)));
            }
        }
        return output.toString();
    }
    
    // decrypt the input string
    public String decrypt(String input) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        String output = cc.encrypt(input);
        return output;
    }
}
