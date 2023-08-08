import edu.duke.*;
import java.util.*;
import java.io.*;
public class GladLib {
    private ArrayList<String> adjectiveList;
    private ArrayList<String> nounList;
    private ArrayList<String> colorList;
    private ArrayList<String> countryList;
    private ArrayList<String> nameList;
    private ArrayList<String> animalList;
    private ArrayList<String> timeList;
    private ArrayList<String> verbList;
    private ArrayList<String> fruitList;
    private ArrayList<String> usedList;
    private int countRep;
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLib(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        usedList = new ArrayList<String>();
    }
    
    public GladLib(String source){
        initializeFromSource(source);
        countRep = 0;
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        adjectiveList= readIt(source+"/adjective.txt"); 
        nounList = readIt(source+"/noun.txt");
        colorList = readIt(source+"/color.txt");
        countryList = readIt(source+"/country.txt");
        nameList = readIt(source+"/name.txt");      
        animalList = readIt(source+"/animal.txt");
        timeList = readIt(source+"/timeframe.txt"); 
        verbList = readIt(source+"/verb.txt");
        fruitList = readIt(source+"/fruit.txt");
        
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("country")) {
            return randomFrom(countryList);
        }
        if (label.equals("color")){
            return randomFrom(colorList);
        }
        if (label.equals("noun")){
            return randomFrom(nounList);
        }
        if (label.equals("name")){
            return randomFrom(nameList);
        }
        if (label.equals("adjective")){
            return randomFrom(adjectiveList);
        }
        if (label.equals("animal")){
            return randomFrom(animalList);
        }
        if (label.equals("timeframe")){
            return randomFrom(timeList);
        }
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        if (label.equals("verb"))
        {
            return ""+randomFrom(verbList);
        }
        if (label.equals("fruit"))
        {
            return ""+randomFrom(fruitList);
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
                        rep=0;
                        story = story + sub + " ";
                    }
                    else
                    {
                       sub=processWord(word); 
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
    
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        countRep=0;
        usedList.clear();
    }

}