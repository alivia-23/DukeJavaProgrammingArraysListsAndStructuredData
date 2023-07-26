
/**
 * Write a description of class TestCeaserCipher here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCeaserCipher {
    
    private int[] countLetters(String encrypted) {
        int[] counts = new int[26];
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        
        for (int i = 0; i < encrypted.length(); i++) {
            int index = alphabet.indexOf(Character.toLowerCase(encrypted.charAt(i)));
            
            if (index != -1) {
                counts[index]++;
            }
        }
        
        return counts;
    }
    
    public int indexOfMax (int[] values) {
        if (values.length == 0) return -1;

        int maxIdx = 0;
        for (int k = 0; k < values.length; k++)
            if (values[k] > values[maxIdx])
                maxIdx = k;
        return maxIdx;
    }
    
    private String breakCeasarCipher(String input) {
        int[] freqs = countLetters(input);
        int maxDex = indexOfMax(freqs);
        int dkey = maxDex - 4;
        
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        
        CeaserCipher cc = new CeaserCipher(dkey);
		
        return cc.decrypt(input);
    }
    
    public void simpleTests() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        CeaserCipher cc = new CeaserCipher(18);
		
        String encrypted = cc.encrypt(message);
        System.out.println("Encryption");
        System.out.println("==========");
        System.out.println(message + " -> " + encrypted);
        
        String decrypted = cc.decrypt(encrypted);
        System.out.println("Decryption");
        System.out.println("==========");
        System.out.println(encrypted + " -> " + decrypted);

        decrypted = breakCeasarCipher(encrypted);
        System.out.println("Break Caesar Cipher");
        System.out.println("===================");
        System.out.println(encrypted + " -> " + decrypted);
    }
}
