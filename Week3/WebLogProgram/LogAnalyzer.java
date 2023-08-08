
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
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}
