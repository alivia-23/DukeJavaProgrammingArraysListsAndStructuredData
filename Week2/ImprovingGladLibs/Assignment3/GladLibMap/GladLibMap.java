
/**
 * Write a description of class GladLibMap here.
 *
 * @author (Alivia Guin)
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;

public class GladLibMap
{
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> usedList;
    private ArrayList<String> usedCategories;
    private int countRep;
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        usedList=new ArrayList<String>();
        usedCategories = new ArrayList<String>();
    }
    
    public GladLibMap(String source){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        countRep=0;
        myRandom = new Random();
        usedList=new ArrayList<String>();
        
    }
    
    private void initializeFromSource(String source) {
        String[] labels={"adjective","noun","color","country","name","animal","timeframe","verb","fruit"};
        for (String s : labels)
        {
            myMap.put(s,readIt(source+"/"+s+".txt"));
        }   
        
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        
        for (String s:myMap.keySet())
        {
            if (s.equals(label))
            {
                ArrayList<String> arr = myMap.get(s);
                return randomFrom(arr);
            }
        }
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first+1);
        if (first == -1 || last == -1){
            return w;
        }
        //System.out.println("To be replaced: "+w);
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        //System.out.println(w.substring(first+1,last));
        
        if (usedList.isEmpty())
        {
            usedList.add(sub);
            //System.out.println(w+":"+sub);
            //System.out.println(" ");
            countRep++;
            return prefix+sub+suffix;
        }
        if (usedList.contains(sub))
        {
            //System.out.println(w+":"+sub);
            //System.out.println("Repeated");
            return "repeat";
        }
        else
        {
            usedList.add(sub);
            //System.out.println(w+":"+sub);
            //System.out.println(" ");
            countRep++;
            return prefix+sub+suffix;
        }  
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
        System.out.println(" ");
        System.out.println("Total number of words replaced: "+countRep);
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                //System.out.println(word);
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                //System.out.println(word);
                String sub = processWord(word);
                int rep = 1;
                while(rep == 1)
                {
                    
                    if (sub.equals("repeat") == false)
                    {
                        rep = 0;
                        story = story + sub + " ";
                    }
                    else
                    {
                       sub = processWord(word); 
                    }
                }
                
                //System.out.println(story);
                //System.out.println(" ");
            }
        }
        System.out.println(" ");
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public int totalWordsInMap()
    {
        int count = 0;
        for (String s: myMap.keySet())
        {
            ArrayList<String> arr = myMap.get(s);
            count = count + arr.size();
        }
        return count;
    }
    
    public int totalWordsConsidered()
    {
        int count = 0;
        for (String word : usedList)
        {
            for (String s : myMap.keySet())
            {
                ArrayList<String> arr = myMap.get(s);
                if (arr.contains(word))
                {
                    
                    if (usedCategories.size()==0)
                    {
                        usedCategories.add(s);
                        count=count+arr.size();
                        System.out.println(s+":"+arr.size());
                        
                    }
                    else if (!usedCategories.contains(s))
                    {
                        usedCategories.add(s);
                        count=count+arr.size();
                        System.out.println(s+":"+arr.size());
                        
                    }
                    else
                    {
                        continue;
                    }
                }
            }
        }
        System.out.println(" ");
        return count;
    }
    
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("Total number of possible picks: "+totalWordsInMap());
        System.out.println("Total number of words in the used categories is: "+totalWordsConsidered());
        countRep = 0;
        usedList.clear();
        usedCategories.clear();
    }
    
}
