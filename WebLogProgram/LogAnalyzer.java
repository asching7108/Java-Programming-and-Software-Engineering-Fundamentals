
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
    private ArrayList<LogEntry> records;
    
    public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
    }
        
    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        for (String line : fr.lines()) {
            LogEntry log = WebLogParser.parseEntry(line);
            records.add(log);
        }
    }
     
    public int countUniqueIPs() {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records) {
            String ip = le.getIpAddress();
            if (!uniqueIPs.contains(ip)) {
                uniqueIPs.add(ip);
            }
        }
        return uniqueIPs.size();
    }
    
    public void printAllHigherThanNum(int num) {
        for (LogEntry le : records) {
            if (le.getStatusCode() > num) {
                System.out.println(le);
            }
        }
    }
    
    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIPsOnDay = new ArrayList<String>();
        for (LogEntry le : records) {
            String date = le.getAccessTime().toString();
            date = date.substring(4, 10);
            String ip = le.getIpAddress();
            if (date.equals(someday) && !uniqueIPsOnDay.contains(ip)) {
                uniqueIPsOnDay.add(ip);
            }
        }
        return uniqueIPsOnDay;
        
    }
    
    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records) {
            int code = le.getStatusCode();
            String ip = le.getIpAddress();
            if (code >= low && code <= high && !uniqueIPs.contains(ip)) {
                uniqueIPs.add(ip);
            }
        }
        return uniqueIPs.size();
    }
    
    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }
    
    // This method returns a HashMap<String, Integer> that maps an IP 
    // address to the number of times that IP address appears in records, 
    // meaning the number of times this IP address visited the website.
    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for (LogEntry le : records) {
            String ip = le.getIpAddress();
            if (counts.containsKey(ip)) {
                counts.put(ip, counts.get(ip) + 1);
            }
            else {
                counts.put(ip, 1);
            }
        }
        return counts;
    }
    
    // This method returns the maximum number of visits to this website 
    // by a single IP address.
    public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
        int largestNum = 0;
        for (String ip : counts.keySet()) {
            if (counts.get(ip) > largestNum) {
                largestNum = counts.get(ip);
            }
        }
        return largestNum;
    }
    
    // This method returns an ArrayList of Strings of IP addresses that 
    // all have the maximum number of visits to this website.
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts) {
        ArrayList<String> iPs = new ArrayList<String>();
        int largestNum = mostNumberVisitsByIP(counts);
        for (String ip : counts.keySet()) {
            if (counts.get(ip) == largestNum) {
                iPs.add(ip);
            }
        }
        return iPs;
    }
    
    // This method returns a HashMap<String, ArrayList<String>> that uses 
    // records and maps days from web logs to an ArrayList of IP addresses 
    // that occurred on that day (including repeated IP addresses).
    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> dateMap = 
            new HashMap<String, ArrayList<String>>();
        for (LogEntry le : records) {
            String date = le.getAccessTime().toString();
            date = date.substring(4, 10);
            if (!dateMap.containsKey(date)) {
                ArrayList<String> iPs = new ArrayList<String>();
                iPs.add(le.getIpAddress());
                dateMap.put(date, iPs);
            }
            else {
                ArrayList<String> iPs = dateMap.get(date);
                iPs.add(le.getIpAddress());
                dateMap.put(date, iPs);
            }
        }
        return dateMap;
    }
    
    // This method returns the day that has the most IP address visits.
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> dateMap) {
        String mostVisitDate = "";
        int largestNum = 0;
        for (String date : dateMap.keySet()) {
            int iPsCount = dateMap.get(date).size();
            if (iPsCount > largestNum) {
                largestNum = iPsCount;
                mostVisitDate = date;
            }
        }
        return mostVisitDate;
    }
    
    // This method returns an ArrayList<String> of IP addresses that had 
    // the most accesses on the given day.
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> dateMap, 
                                         String date) {
        ArrayList<String> iPs = dateMap.get(date);
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for (String ip : iPs) {
            if (counts.containsKey(ip)) {
                counts.put(ip, counts.get(ip) + 1);
            }
            else {
                counts.put(ip, 1);
            }
        }
        return iPsMostVisits(counts);
    }
}
