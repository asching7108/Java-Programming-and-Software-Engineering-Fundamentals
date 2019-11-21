
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter{
    private Location city;
    private double maxDist;
    
    public DistanceFilter(Location loc, double max) {
        city = loc;;
        maxDist = max;
    }
    
    public String getName() {
        return "Distance";
    }
    
    public boolean satisfies(QuakeEntry qe) {
        return qe.getLocation().distanceTo(city) < maxDist;
    }

}
