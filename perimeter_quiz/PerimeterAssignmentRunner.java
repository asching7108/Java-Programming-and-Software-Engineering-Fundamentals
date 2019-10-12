import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int numPts = 0;
        for (Point pt : s.getPoints()) {
            numPts++;
        }
        return numPts;
    }

    public double getAverageLength(Shape s) {
        double perim = getPerimeter(s);
        int numPts = getNumPoints(s);
        double avgLen = perim / numPts;
        return avgLen;
    }

    public double getLargestSide(Shape s) {
        // Start with largestSide = 0
        double largestSide = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update largestSide if currDist is greater 
            // than the current largestSide
            if (currDist > largestSide) {
                largestSide = currDist;
            }
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // largestSide is the answer
        return largestSide;
    }

    public double getLargestX(Shape s) {
        // Start with largestX = 0
        double largestX = -100.0;
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currX = currPt.getX();
            // Update largestX if currX is greater 
            // than the current largestX
            if (currX > largestX) {
                largestX = currX;
            }
        }
        // largestX is the answer
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Start with largestPerim = 0.0
        double largestPerim = 0.0;
        // Open multiple files
        DirectoryResource dr = new DirectoryResource();
        // For each file
        for (File f : dr.selectedFiles()) {
            // Create a FileResource object by the File object f
            FileResource fr = new FileResource(f);
            // Create a Shape object by the FileResource object fr
            Shape s = new Shape(fr);
            // Find the perimeter of Shape s
            double currPerim = getPerimeter(s);
            // Update largestPerim if currPerim is greater 
            // than the current largestPerim
            if (currPerim > largestPerim) {
                largestPerim = currPerim;
            }
        }
        // largestPerim is the answer
        return largestPerim;
    }
    
    public String getFileWithLargestPerimeter() {
        // Start with temp = null and largestPerim = 0.0
        File temp = null;
        double largestPerim = 0.0;
        // Open multiple files
        DirectoryResource dr = new DirectoryResource();
        // For each file
        for (File f : dr.selectedFiles()) {
            // Create a FileResource object by the File object f
            FileResource fr = new FileResource(f);
            // Create a Shape object by the FileResource object fr
            Shape s = new Shape(fr);
            // Find the perimeter of Shape s
            double currPerim = getPerimeter(s);
            // Update largestPerim if currPerim is greater 
            // than the current largestPerim
            if (currPerim > largestPerim) {
                largestPerim = currPerim;
                temp = f;
            }
        }
        // the name of File temp is the answer
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        int numPts = getNumPoints(s);
        System.out.println("number of points = " + numPts);
        double avgLen = getAverageLength(s);
        System.out.println("average length = " + avgLen);
        double largestSide = getLargestSide(s);
        System.out.println("largest side = " + largestSide);
        double largestX = getLargestX(s);
        System.out.println("largest x = " + largestX);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
    }
    
    public void testPerimeterMultipleFiles() {
        double largestPerim = getLargestPerimeterMultipleFiles();
        System.out.println("largest perimeter = " + largestPerim);
    }

    public void testFileWithLargestPerimeter() {
        String largestPerimFile = getFileWithLargestPerimeter();
        System.out.println("file name of largest perimeter = " + largestPerimFile);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
