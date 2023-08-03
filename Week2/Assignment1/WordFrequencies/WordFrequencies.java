
/**
 * Write a description of class WordFrequencies here.
 *
 * @author (Alivia Guin)
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class WordFrequencies
{
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<>();
        myFreqs = new ArrayList<>();
    }
    
    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        
        for (String word : fr.words()) {
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            // if index is -1 i.e. word is not present in myWords
            if (index == -1) {
                myWords.add(word);
                myFreqs.add(1);
            } else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value + 1); // replace index position value with current value
            }
        }
    }
    
    public int findIndexOfMax() {
        int maxValue = myFreqs.get(0);
        int maxIndex = 0;
        for(int i = 0; i < myFreqs.size(); i++) {
            if(myFreqs.get(i) > maxValue) {
                maxValue = myFreqs.get(i);
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public void tester() {
        findUnique();
        System.out.println("Number of unique words: " +myWords.size());
        for (int i = 0; i < myWords.size(); i++) {
            //String word = myWords.get(i);
            //int index = myWords.indexOf(word);
            //int freq = myFreqs.get(index);
            System.out.println("Freqs: "+myFreqs.get(i)+ " Word" +myWords.get(i));
        }
        
        int maxIndex = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: "+
                myWords.get(maxIndex)+" "+myFreqs.get(maxIndex));
    }
}
