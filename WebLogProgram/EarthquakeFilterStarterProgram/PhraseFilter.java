
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{
        private String where;
        private String phrase;
    
    public PhraseFilter(String whereIn, String phraseIn) {
        where = whereIn;
        phrase = phraseIn;
    }
    
    public String getName() {
        return "Phrase";
    }
    
    public boolean satisfies(QuakeEntry qe) {
        String title = qe.getInfo();
        boolean ans = false;
        if (title.contains(phrase)) {
            int lenP = phrase.length();
            int lenT = title.length();
            if (where.equals("any") || 
                (where.equals("start") && title.substring(0, lenP).equals(phrase)) || 
                (where.equals("end") && title.substring(lenT - lenP, lenT).equals(phrase))) {
                ans = true;
            }
        }
        return ans;
    }

}
