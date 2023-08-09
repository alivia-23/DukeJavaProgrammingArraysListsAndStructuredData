
/**
 * Write a description of class Tester here.
 * 
 * @author (Alivia Guin) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;
import edu.duke.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        //la.readFile("short-test_log");
        la.readFile("weblog2_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are " +uniqueIPs + " IPs");
    }
    
    public void testLogHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        //la.readFile("short-test_log");
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        //la.readFile("weblog-short_log");
        //la.readFile("weblog1_log");
        la.readFile("weblog2_log");
        //int count1 = la.uniqueIPVisitsOnDay("Sep 14").size();
        //int count2 = la.uniqueIPVisitsOnDay("Sep 30").size();
        int count3 = la.uniqueIPVisitsOnDay("Sep 24").size();
        //System.out.println("Number of unique IPs on Sep 14 " +count1);
        //System.out.println("Number of unique IPs on Sep 30 " +count2);
        System.out.println("Number of unique IPs on Sep 24 " +count3);
    }
    
    public void testCountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        //la.readFile("short-test_log");
        //la.readFile("weblog1_log");
        la.readFile("weblog2_log");
        //int count1 = la.countUniqueIPsInRange(200,299);
        //int count2 = la.countUniqueIPsInRange(300,399);
        int count = la.countUniqueIPsInRange(400,499);
        //System.out.println("Number of unique IPs in the range 200 and 299 " +count1);
        System.out.println("Number of unique IPs in the range 400 and 499 " +count);
    }
    
    public void testCountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println("Number of visits per IP " +counts);
    }
    
    public void testMostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        //la.readFile("weblog3-short_log");
        //la.readFile("weblog1_log");
        la.readFile("weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        int count = la.mostNumberVisitsByIP(counts);
        System.out.println("Most number of visits by an IP " +count);
    }
    
     public void testIPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        //la.readFile("weblog3-short_log");
        //la.readFile("weblog1_log");
        la.readFile("weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        ArrayList<String> ipAddresses = la.iPsMostVisits(counts);
        int count = ipAddresses.size();
        System.out.println("Most visit ips "+count);
        for (String ip : ipAddresses) {
            System.out.println(ip);
        }
    }
    
    public void testIPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        //la.readFile("weblog3-short_log");
        //la.readFile("weblog1_log");
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> dayForIps = la.iPsForDays();
        for (String day : dayForIps.keySet()) {
            System.out.println(day + " ==> " + dayForIps.get(day));
        }
    }
    
    public void testDayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        //la.readFile("weblog3-short_log");
        //la.readFile("weblog1_log");
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> dayForIps = la.iPsForDays();
        String maxIpsDay = la.dayWithMostIPVisits(dayForIps);
        System.out.println("Day with most IP visits " +maxIpsDay);
    }
    
    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        //la.readFile("weblog3-short_log");
        la.readFile("weblog1_log");
        //la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> dayForIps = la.iPsForDays();
        //String day = la.dayWithMostIPVisits(dayForIps);
        //String day = "Mar 17";
        String day = "Sep 29";
        ArrayList<String> ips = la.iPsWithMostVisitsOnDay(dayForIps, day);
        System.out.println("Most visiting IPs on a Day "+ day + " are "+ips); 
    }
}
