
/**
 * Write a description of class CharactersInPlay here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class CharactersInPlay
{
    private ArrayList<String> names;
    private ArrayList<Integer> freqs;
    
    public CharactersInPlay() {
        names = new ArrayList<>();
        freqs = new ArrayList<>();
    }
    
    public void update(String person) {
        int index = names.indexOf(person);
        if (!names.contains(person)) {
            names.add(person);
            freqs.add(1);
        } else {
            int value = freqs.get(index);
            freqs.set(index, value+1);
        }
    }
    
    public void findAllCharacters() {
        names.clear();
        freqs.clear();
        FileResource fr = new FileResource();
        for (String line : fr.lines()) {
             if(line.contains(".")){
                line = line.trim();
                int index = line.indexOf(".");
                if (index != -1) {
                    String person = line.substring(0, index);
                    update(person);
                }
             }
        }
    }
    
    public void charactersWithNumParts(int num1, int num2) {
         for (int i = 0; i < names.size(); i++) {
            int numParts = freqs.get(i);
            if(numParts >= num1 && numParts <= num2) {
                System.out.println("NAMES ==> " +names.get(i) + " " + "NUMBER OF PARTS" + numParts);
            }
         }
    }
    
    public void tester() {
        findAllCharacters();
        for (int i = 0; i < names.size(); i++) {
            if(freqs.get(i) > 1) {
                System.out.println(names.get(i) +
                                "  number of speaking parts: " + freqs.get(i));
            }
        }
        charactersWithNumParts(10, 5);
        System.out.println("*******************************************");
    }
}
