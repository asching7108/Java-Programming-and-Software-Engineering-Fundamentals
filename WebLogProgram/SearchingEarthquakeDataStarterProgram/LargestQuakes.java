/**
 * Find the largest magnitude quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class LargestQuakes {
    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        int maxIndex = 0;
            for (int i = 0; i < data.size(); i++) {
                QuakeEntry qe = data.get(i);
                if (qe.getMagnitude() > 
                    data.get(maxIndex).getMagnitude()) {
                    maxIndex = i;
                }
            }
        return maxIndex;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        for (int i = 0; i < howMany; i++) {
            int maxIndex = indexOfLargest(copy);
            ret.add(copy.get(maxIndex));
            copy.remove(maxIndex);
        }
        return ret;
    }
    
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        ArrayList<QuakeEntry> large = getLargest(list,50);
        for(QuakeEntry qe : large){
            System.out.println(qe);
        }
    }
}
