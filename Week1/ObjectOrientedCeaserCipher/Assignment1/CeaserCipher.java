
/**
 * Write a description of class CeaserCipher here.
 *
 * @author (Alivia Guin)
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CeaserCipher {
    private String alphabet;
    private String shiftedAlphabet;
    int key;
    
    public CeaserCipher(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key)+alphabet.substring(0,key);
        this.key = key;
    }
    
    public String encrypt(String input) {
        StringBuilder encryptedMessage = new StringBuilder();
        
        for (int i = 0; i < input.length(); i++) {
            char currentCharacter = input.charAt(i);
            int index = alphabet.toLowerCase().indexOf(Character.toLowerCase(currentCharacter));
            
            if (index != -1) {
                if (Character.isLowerCase(currentCharacter)) {
                    encryptedMessage.append(Character.toLowerCase(shiftedAlphabet.charAt(index)));                
                } else {
                    encryptedMessage.append(shiftedAlphabet.charAt(index));
                }
            } else {
                encryptedMessage.append(currentCharacter);
            }
        }
        
        return encryptedMessage.toString();
    }
    
    public String decrypt(String input) {
        CeaserCipher cc = new CeaserCipher(26 - key);
        return cc.encrypt(input);
    }
    
}
