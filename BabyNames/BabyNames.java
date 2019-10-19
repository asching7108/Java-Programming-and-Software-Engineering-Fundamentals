

/**
 * This program is a mini program of baby names.
 * @author Hsingyi Lin
 * @version 08/30/2019
 */
import edu.duke.*;
import java.io.File;
import org.apache.commons.csv.*;

public class BabyNames {
    public void totalBirths(FileResource fr) {
        int totalBirths = 0;
        int totalGirls = 0;
        int totalBoys = 0;
        int totalGirlNames = 0;
        int totalBoyNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("F")) {
                totalGirls += numBorn;
                totalGirlNames++;
            }
            else {
                totalBoys += numBorn;
                totalBoyNames++;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("total girls = " + totalGirls);
        System.out.println("total boys = " + totalBoys);
        System.out.println("total girl names = " + totalGirlNames);
        System.out.println("total boy names = " + totalBoyNames);
    }
    
    public int getRank(int year, String name, String gender) {
        FileResource fr = new FileResource("us_babynames_by_year/yob" + year + ".csv");
        int rank = 1;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender) && 
                rec.get(0).equals(name)) {
                return rank;
            }
            else if (rec.get(1).equals(gender)) {
                rank++;
            }
        }
        return -1;
    }
    
    public String getName(int year, int rank, String gender) {
        int currentRank = 0;
        FileResource fr = new FileResource("us_babynames_by_year/yob" + year + ".csv");
        // get the name of the given rank and gender
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                currentRank++;
                if (currentRank == rank) {
                    return rec.get(0);
                }
            }
        }
        return "NO NAME";
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, 
                                   String gender) {
        int rank = getRank(year, name, gender);
        System.out.println("his/her rank is: " + rank);
        String newName = getName(newYear, rank, gender);
        System.out.println(name + " born in " + year + " would be " + 
                           newName + " if he/she was born in " + 
                           newYear + ".");
    }
    
    public int yearOfHighestRank(String name, String gender) {
        int topRank = -1;
        int topYear = -1;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.substring(3, 7));
            int currentRank = getRank(year, name, gender);
            if ((topRank == -1 && currentRank != -1) ||
                (currentRank != -1 && currentRank < topRank)) {
                topRank = currentRank;
                topYear = year;
            }
        }
        return topYear;
    }
    
    public double getAverageRank(String name, String gender) {
        double sumRank = 0.0;
        int numYear = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.substring(3, 7));
            int currentRank = getRank(year, name, gender);
            if (currentRank != -1) {
                sumRank += currentRank;
                numYear++;
            }
        }
        if (numYear == 0) {
            return -1.0;
        }
        return sumRank / numYear;
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        FileResource fr = new FileResource("us_babynames_by_year/yob" + year + ".csv");
        int totalBirths = 0;
        int targetRank = getRank(year, name, gender);
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender) &&
                (targetRank == -1 || 
                getRank(year, rec.get(0), gender) < targetRank)) {
                totalBirths += Integer.parseInt(rec.get(2));
            }
        }
        return totalBirths;
    }
    
    public void testTotalBirths() {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
}
