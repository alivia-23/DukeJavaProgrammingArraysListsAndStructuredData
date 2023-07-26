import edu.duke.*;

public class CaesarCipher {
    
    // The source alphabet used by all routines.
    private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    // check if a string has something in it
    private boolean hasValue(String s) { 
        return s != null && ! s.isEmpty(); 
    }
    
    public String encrypt(String input, int key) {
        if (!hasValue(input)) return "";
        if (key == 0) return input;
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //for lowercase alphabets
        String lowAlphabet = alphabet.toLowerCase();
        //Compute the shifted alphabet
        String shiftedAlphabet = alphabet.substring(key)+
        alphabet.substring(0,key);
        // for lowercase shifted alphabets
        String lowerShiftedAlphabet = lowAlphabet.substring(key)+
        lowAlphabet.substring(0,key);
        
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            
            if (Character.isLowerCase(currChar)) {
                //Find the index of currChar in the alphabet (call it idx)
                int idx = lowAlphabet.indexOf(currChar);
                //If currChar is in the alphabet
                if (idx != -1){
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = lowerShiftedAlphabet.charAt(idx);
                    //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newChar);
                }
            } else {
                //Find the index of currChar in the alphabet (call it idx)
                int idx = alphabet.indexOf(currChar);
                //If currChar is in the alphabet
                if(idx != -1){
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = shiftedAlphabet.charAt(idx);
                    //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newChar);
                }
            }
            
        }
        return encrypted.toString();
    }
    
    public void testCaesar() {
        //int key = 23;
        //String input = "First Legion";
        //System.out.println(encrypt(input, key));
        int key = 17;
        String input = "First Legion";
        System.out.println(encrypt(input, key));
        /*String input = "FIRST LEGION ATTACK EAST FLANK!";
        System.out.println(encrypt(input, key));
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        System.out.println("key is " + key + "\n" + encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
        System.out.println("key is " + key + "\n" + decrypted);*/
    }
    
    private String shiftAlphabet(int key) {
        return alphabet.substring(key) + alphabet.substring(0, key);
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        if (!hasValue(input)) return "";
        if (key1 == 0 && key2 == 0) return input;
        
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        
        String myAlphabet = alphabet.toUpperCase() + alphabet.toLowerCase();
        String shifted1 = shiftAlphabet(key1).toUpperCase() +
                shiftAlphabet(key1).toLowerCase();
        String shifted2 = shiftAlphabet(key2).toUpperCase() +
                shiftAlphabet(key2).toLowerCase();
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = myAlphabet.indexOf(currChar);
            if (idx != -1) {
                // Which alphabet do I use?  even i is key1, odd i is key2
                String shiftedAlphabet = (i % 2 == 0) ? shifted1: shifted2;
                char newChar = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }
    
    public void testEncryptTwoKeys() {
        int key1 = 23;
        int key2 = 17;
        String input = "First Legion";
        System.out.println(encryptTwoKeys(input, key1, key2));
    }
    
    
    
}

