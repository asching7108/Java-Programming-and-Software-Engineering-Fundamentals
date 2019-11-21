
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter{
    private double minDepth;
    private double maxDepth;
    
    public DepthFilter(double min, double max) {
        minDepth = min;
        maxDepth = max;
    }
    
    public String getName() {
        return "Depth";
    }
    
    public boolean satisfies(QuakeEntry qe) {
        double depth = qe.getDepth();
        return depth >= minDepth && depth <= maxDepth;
    }

}
