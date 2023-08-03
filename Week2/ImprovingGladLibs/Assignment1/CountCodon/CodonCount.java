
/**
 * Write a description of class CodonCount here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class CodonCount
{
    private HashMap<String, Integer> counts;

    public CodonCount() {
        counts = new HashMap<String, Integer>();
    }
    public void buildCodonMap(int start, String dna) {
        dna = dna.toUpperCase();
        for(int i = start; i < dna.length(); i+=3) {
            if(!(i + 3 >= dna.length())) {
                String codon = dna.substring(i, i+3);
                if(!counts.containsKey(codon)) {
                    counts.put(codon, 1);
                } else {
                    counts.put(codon, counts.get(codon) + 1);
                }
            }
        }
    }
    
    public String getMostCommonCodon() {
        int largest = 0;
        String largestCodon = "";
        for(String codon : counts.keySet()) {
            int currentCount = counts.get(codon);
            if(currentCount > largest) {
                largest = currentCount;
                largestCodon = codon;
            }
        }
        return largestCodon;
    }
    
    void printCodonCounts(int start, int end) {
        for(String codon : counts.keySet()) {
            int count = counts.get(codon);
            if(count >= start && count <= end) {
                System.out.println(codon + "\t" + count);
            }
        }
        System.out.println("Unique Codons: " + counts.size());
    }
    
    void tester() {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        //dna = dna.toUpperCase();
        //buildCodonMap(1, dna);
        //buildCodonMap(0, dna);
        counts.clear();
        //buildCodonMap(2, dna); //39
        buildCodonMap(2, dna); //34
        //buildCodonMap(0, dna); //36
        printCodonCounts(1,50); 
        System.out.println("Most Common Codon : " + getMostCommonCodon());
    }
}
