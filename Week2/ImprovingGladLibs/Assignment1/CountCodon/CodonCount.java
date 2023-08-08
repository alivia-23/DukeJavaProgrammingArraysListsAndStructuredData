
/**
 * Write a description of class CodonCount here.
 *
 * @author (Alivia Guin)
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;

public class CodonCount
{
    private int frame,start,end;
    private HashMap<String,Integer> codonCount;
    
    public CodonCount()
    {
        codonCount = new HashMap<String,Integer>();
        frame = 0;
        start = 1;
        end = 7;
    }
    
    public void buildCodonMap(int start, String dna)
    {
        codonCount.clear();
        dna = dna.trim();
        //System.out.println(dna.substring(start));
        for(int i = start;(i+2) < dna.length();i = i+3)
        {
            String sub=dna.substring(i,i+3);
            //System.out.println(i+" "+sub);
            if(codonCount.containsKey(sub))
            {
                codonCount.put(sub,codonCount.get(sub)+1);
            }
            else
            {
                codonCount.put(sub,1);
            }
        }     
    }
    
    public String getMostCommonCodon()
    {
        int max = -1;
        String codon = "";
        for (String s : codonCount.keySet())
        {
            int i = codonCount.get(s);
            if(max < i)
            {
                max = i;
                codon = s;
            }
        }
        return codon;
    }
    
    public void printCodonCounts(int start, int end)
    {
        System.out.println("Reading frame "+frame+".......");
        System.out.println("Number of unique codons: "+codonCount.size());
        String codon = getMostCommonCodon();
        System.out.println("Most common codon is: "+codon+" and the count is: "+codonCount.get(codon));
        System.out.println("Counts of codon between "+start+" and "+end+": ");
        for (String s : codonCount.keySet())
        {
            int i = codonCount.get(s);
            if(start <= i && i <= end)
            {
                System.out.println(s+"\t"+i);
            }
        }      
        System.out.println(" ");
        
    }
    
    public void tester()
    {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        dna = dna.trim();
        
        buildCodonMap(frame, dna);
        printCodonCounts(start, end);
        frame++;
        
        buildCodonMap(frame, dna);
        printCodonCounts(start, end);
        frame++;
        
        buildCodonMap(0, dna);
        printCodonCounts(start, end);
        frame = 0;
    }

}
