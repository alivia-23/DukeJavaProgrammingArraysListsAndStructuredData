
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
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
     
     
}
