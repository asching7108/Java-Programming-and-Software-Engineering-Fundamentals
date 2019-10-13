
/**
 * This program parses the weather data.
 * @author Hsingyi Lin
 * @version 08/30/2019
 */
import edu.duke.*;
import java.io.File;
import org.apache.commons.csv.*;

public class ParsingWeatherData {
    public CSVRecord coldestOfTwo(CSVRecord currentRow, CSVRecord coldestHour) {
        if (coldestHour == null) {
            coldestHour = currentRow;
        }
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double coldestTemp = Double.parseDouble(coldestHour.get("TemperatureF"));
            if (currentTemp != -9999 && 
                currentTemp < coldestTemp) {
                coldestHour = currentRow;    
            }
        }
        return coldestHour;
    }

    public CSVRecord lowestOfTwo(CSVRecord currentRow, CSVRecord lowestHumidity) {
        if (lowestHumidity == null) {
            return currentRow;
        }
        else {
            if (currentRow.get("Humidity").equals("N/A")) {
                return lowestHumidity;
            }
            if (lowestHumidity.get("Humidity").equals("N/A")) {
                return currentRow;
            }
            int currentTemp = Integer.parseInt(currentRow.get("Humidity"));
            int lowestTemp = Integer.parseInt(lowestHumidity.get("Humidity"));
            if (currentTemp != -9999 && 
                currentTemp < lowestTemp) {
                return currentRow;    
            }
        }
        return lowestHumidity;
    }

    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestHour = null;
        for (CSVRecord currentRow : parser) {
            coldestHour = coldestOfTwo(currentRow, coldestHour);
        }
        return coldestHour;
    }
    
    public String fileWithColdestTemperature() {
        DirectoryResource dr = new DirectoryResource();
        File coldestFile = null;
        CSVRecord coldestHour = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentFile = coldestHourInFile(parser);
            if (coldestFile == null) {
                coldestFile = f;
                coldestHour = currentFile;
            }
            else {
                double currentTemp = Double.parseDouble(currentFile.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestHour.get("TemperatureF"));
                if (currentTemp != -9999 && 
                    currentTemp < coldestTemp) {
                    coldestFile = f;
                    coldestHour = currentFile;
                }
            }
        }
        return coldestFile.getName();
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestHumidity = null;
        for (CSVRecord currentRow : parser) {
            lowestHumidity = lowestOfTwo(currentRow, lowestHumidity);
        }
        return lowestHumidity;
    }
    
    public CSVRecord lowestHumidityInManyFiles() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestHumidity = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentFile = lowestHumidityInFile(parser);
            lowestHumidity = lowestOfTwo(currentFile, lowestHumidity);
        }
        return lowestHumidity;
    }
    
    public double averageTemperatureInFile(CSVParser parser) {
        double sumTemp = 0;
        int rows = 0;
        for (CSVRecord currentRow : parser) {
            if (currentRow.get("TemperatureF") != "-9999") {
                rows++;
                sumTemp += Double.parseDouble(currentRow.get("TemperatureF"));
            }
        }
        if (rows == 0) {
            return -9999;
        }
        return sumTemp / rows;
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, 
                                                           int value) {
        double sumTemp = 0.0;
        int rows = 0;
        for (CSVRecord currentRow : parser) {
            if (!currentRow.get("Humidity").equals("N/A") &&
                Integer.parseInt(currentRow.get("Humidity")) >= value && 
                !currentRow.get("TemperatureF").equals("-9999")) {
                rows++;
                sumTemp += Double.parseDouble(currentRow.get("TemperatureF"));
            }
        }
        if (rows == 0) {
            return -9999;
        }
        return sumTemp / rows;
    }
    
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord record = coldestHourInFile(parser);
        System.out.println(record.get("DateUTC") + ": " + 
                           record.get("TemperatureF"));
    }
    
    public void testFileWithColdestTemperature() {
        String fileName = fileWithColdestTemperature();
        System.out.println(fileName);
        FileResource fr = new FileResource("2013/" + fileName);
        CSVParser parser = fr.getCSVParser();
        CSVRecord record = coldestHourInFile(parser);
        System.out.println("Coldest day was in file " + fileName);
        System.out.println("Coldest temperature of that day was " + 
                           record.get("TemperatureF"));
        System.out.println("All the temperatures on the coldest day were: ");
        parser = fr.getCSVParser();
        for (CSVRecord currentRow : parser) {
            System.out.println(currentRow.get("DateUTC") + ": " + 
                               currentRow.get("TemperatureF"));
        }
    }

    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord record = lowestHumidityInFile(parser);
        System.out.println(record.get("DateUTC") + ": " + 
                           record.get("Humidity"));
    }
    
    public void testLowestHumidityInManyFiles() {
        CSVRecord record = lowestHumidityInManyFiles();
        System.out.println(record.get("DateUTC") + ": " + 
                           record.get("Humidity"));
    }
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgTemp = averageTemperatureInFile(parser);
        if (avgTemp == -9999) {
            System.out.println("No data");
        }
        else {
            System.out.println(avgTemp);
        }
    }

    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgTemp = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (avgTemp == -9999) {
            System.out.println("No temperatures with that humidity");
        }
        else {
            System.out.println(avgTemp);
        }
    }
}
