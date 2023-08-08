
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (Alivia Guin) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;
import java.io.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<>();
     }
        
     public void readFile(String filename) {
         FileResource logs = new FileResource(filename);
         for (String log : logs.lines()) {
             LogEntry obj = WebLogParser.parseEntry(log);
             records.add(obj);
         }
     }
     
     public int countUniqueIPs() {
         ArrayList<String> uniqueIPs = new ArrayList<>();
         for (LogEntry le : records) {
             String ipAddress = le.getIpAddress();
             if (!uniqueIPs.contains(ipAddress)) {
                 uniqueIPs.add(ipAddress);
             }
         }
         return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum(int num) {
         for (LogEntry le : records) {
             int statusCode = le.getStatusCode();
             if (statusCode > num) {
                 System.out.println(le);
             }
         }
         
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
         ArrayList<String> uniqueIPs = new ArrayList<>();
         for (LogEntry le : records) {
             String date = le.getAccessTime().toString().substring(4,10);
             if (date.equals(someday)) {
                 String ipAddress = le.getIpAddress();
                 if (!uniqueIPs.contains(ipAddress)) {
                     uniqueIPs.add(ipAddress);
                 }
             }
         }
         return uniqueIPs;
     }
     
     public int countUniqueIPsInRange(int low, int high) {
         ArrayList<String> uniqueIPs = new ArrayList<>();
         for (LogEntry le : records) {
             int statusCode = le.getStatusCode();
             if (statusCode >= low && statusCode <= high) {
                 String ipAddress = le.getIpAddress();
                 if (!uniqueIPs.contains(ipAddress)) {
                     uniqueIPs.add(ipAddress);
                 }
             }
         }
         return uniqueIPs.size();
     }
     
     public HashMap<String, Integer> countVisitsPerIP() {
         HashMap<String, Integer> counts = new HashMap<>();
         
         for (LogEntry le : records) {
             String ipAddress = le.getIpAddress();
             if (!counts.containsKey(ipAddress)) {
                 counts.put(ipAddress, 1);
             } else {
                 counts.put(ipAddress, counts.get(ipAddress) + 1);
             }
         }
         return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
        if (counts == null || counts.isEmpty()) return 0;

        int maxVisit = 0;
        for (String address : counts.keySet()) {
            int count = counts.get(address);
            if (count > maxVisit) {
                maxVisit = count;
            }
        }
        return maxVisit;
    }
    
    public ArrayList<String> iPsMostVisits (HashMap<String, Integer> counts) {
        ArrayList<String> results = new ArrayList<String>();
        if (counts == null || counts.isEmpty()) return results;

        // get maximum visits by an IP
        int maxVisits = mostNumberVisitsByIP(counts);

        // Now we can search counts for value == maxVisits & put key into results.
        for (String key : counts.keySet()) {
            if (counts.get(key) == maxVisits) {
                results.add(key);
            }
        }
        return results;
    }
    
    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> dayOfIPs = new HashMap<>();
        for (LogEntry le : records) {
            String day = le.getAccessTime().toString().substring(4,10);
            ArrayList<String> ips = new ArrayList<>();
            String ip = le.getIpAddress();
            if (!dayOfIPs.containsKey(day)) {
                ips.add(ip);
                dayOfIPs.put(day, ips);
            } else {
                ips = dayOfIPs.get(day);
                ips.add(ip);
                dayOfIPs.put(day, ips);
            }
        }
        return dayOfIPs;
    }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
     
     
}
