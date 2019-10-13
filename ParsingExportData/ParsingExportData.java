
/**
 * This program parses the export data.
 * @author Hsingyi Lin
 * @version 08/30/2019
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class ParsingExportData {
    public void countryInfo(CSVParser parser, String country) {
        boolean isFound = false;
        for (CSVRecord record : parser) {
            String countryName = record.get("Country");
            if (countryName.equals(country)) {
                System.out.println(countryName + ": " + 
                                   record.get("Exports") + ": " + 
                                   record.get("Value (dollars)"));
                isFound = true;
            }
        }
        if (!isFound) {
            System.out.println("NOT FOUND");
        }
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, 
                                         String exportItem2) {
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && 
                exports.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int numCountry = 0;
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem)) {
                numCountry++;
            }
        }
        return numCountry;
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            String value = record.get("Value (dollars)");
            if (value.length() > amount.length()) {
                System.out.println(record.get("Country") + " " + value);
            }
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        countryInfo(parser, "Nauru");
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "cotton", "flowers");
        parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser, "cocoa"));
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999"); 
    }
}
