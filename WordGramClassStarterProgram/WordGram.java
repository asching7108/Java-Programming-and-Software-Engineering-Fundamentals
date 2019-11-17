
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
        myHash = toString().hashCode();
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        for (int i = 0; i < myWords.length; i++) {
            ret += myWords[i] + " ";
        }
        return ret.trim();
    }

    @Override
    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if (myWords.length != other.length()) {
            return false;
        }
        for (int i = 0; i < myWords.length; i++) {
            if (!myWords[i].equals(other.wordAt(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return myHash;
    }
    
    public WordGram shiftAdd(String word) {
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        String[] shift = new String[myWords.length];
        for (int i = 0; i < myWords.length - 1; i++) {
            shift[i] = myWords[i + 1];
        }
        shift[myWords.length - 1] = word;
        WordGram out = new WordGram(shift, 0, myWords.length);
        return out;
    }

}