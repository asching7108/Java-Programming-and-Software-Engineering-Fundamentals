
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter{
    private double minMag;
    private double maxMag;
    
    public MagnitudeFilter(double min, double max) {
        minMag = min;
        maxMag = max;
    }
    
    public String getName() {
        return "Magnitude";
    }
    
    public boolean satisfies(QuakeEntry qe) {
        double mag = qe.getMagnitude();
        return mag >= minMag && mag <= maxMag;
    }

}
