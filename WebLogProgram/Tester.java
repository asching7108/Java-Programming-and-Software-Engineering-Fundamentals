
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog2_log");
        analyzer.printAll();
    }
    
    public void testUniqueIP() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog2_log");
        int count = analyzer.countUniqueIPs();
        System.out.println("the number of unique IPs: " + count);
    }
    
    public void testPrintAllHigherThanNum() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog2_log");
        analyzer.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog2_log");
        String date = "Sep 24";
        ArrayList<String> uniqueIpsOnDay = analyzer.uniqueIPVisitsOnDay(date);
            System.out.println("the number of unique IPs on " + date + ": " + 
                               uniqueIpsOnDay.size());
        for (String ip : uniqueIpsOnDay) {
            System.out.println(ip);
        }
    }

    public void testUniqueIPsInRange() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog2_log");
        int low = 200;
        int high = 299;
        int count = analyzer.countUniqueIPsInRange(low, high);
        System.out.println("the number of unique IPs in " + low + " and " + 
                           high + ": " + count);
    }
    
    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog2_log");
        HashMap<String, Integer> counts = analyzer.countVisitsPerIP();
        System.out.println("countVisitsPerIP: \n" + counts);
        
        int mostVisitsByIP = analyzer.mostNumberVisitsByIP(counts);
        System.out.println("mostVisitsByIP: \n" + mostVisitsByIP);
        
        ArrayList<String> iPsMostVisits = analyzer.iPsMostVisits(counts);
        System.out.println("iPsMostVisits: \n" + iPsMostVisits);
        
        HashMap<String, ArrayList<String>> dateMap = analyzer.iPsForDays();
        System.out.println("dateMap: \n" + dateMap);
        
        String mostVisitDate = analyzer.dayWithMostIPVisits(dateMap);
        System.out.println("mostVisitDate: \n" + mostVisitDate);
        
        String date = "Sep 29";
        ArrayList<String> iPsMostVisitsOnDay = 
            analyzer.iPsWithMostVisitsOnDay(dateMap, date);
        System.out.println("iPsMostVisitsOnDay: \n" + iPsMostVisitsOnDay);
    }
}
