
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class Tester {
    public void testGetFollows() {
        MarkovOne test = new MarkovOne();
        test.setTraining("this is a test yes this is a test.");
        String key = "t";
        ArrayList<String> follows1 = test.getFollows(key);
        System.out.println("Key " + key + " " + follows1);
        System.out.println("follow set size " + follows1.size());
        key = ".";
        ArrayList<String> follows2 = test.getFollows(key);
        System.out.println("Key " + key + " " + follows2);
        System.out.println("follow set size " + follows2.size());
        key = "t.";
        ArrayList<String> follows3 = test.getFollows(key);
        System.out.println("Key " + key + " " + follows3);
        System.out.println("follow set size " + follows2.size());
    }
    
    public void testGetFollowsWithFile() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne test = new MarkovOne();
        test.setTraining(st);
        String key = "he";
        ArrayList<String> follows1 = test.getFollows(key);
        System.out.println("Key " + key + " " + follows1);
        System.out.println("follow set size " + follows1.size());
    }
}
