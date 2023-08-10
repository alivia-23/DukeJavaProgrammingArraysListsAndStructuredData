import java.util.*;
import edu.duke.*;

public class VigenereBreakerHashSet {
    /**
     * Returns a String consisting of every totalSlices-th character from message, 
     * starting at the whichSlice-th character.
     * @parameter message - a String representing the encrypted message
     * @parameter whichSlice - an int indicating the index the slice should start from
     * @parameter totalSlices - an int indicating the length of the key
     */
    public String sliceString(String message, int whichSlice, int totalSlices) {
        String sliced = "";
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            char letter = message.charAt(i);
            sliced += letter;
        }
        return sliced;
    }
    
    public void testSliceString() {
        System.out.println(sliceString("abcdefghijklm", 0, 3).equals("adgjm"));
        System.out.println(sliceString("abcdefghijklm", 1, 3).equals("behk"));
        System.out.println(sliceString("abcdefghijklm", 2, 3).equals("cfil"));
        System.out.println(sliceString("abcdefghijklm", 0, 4).equals("aeim"));
        System.out.println(sliceString("abcdefghijklm", 1, 4).equals("bfj"));
        System.out.println(sliceString("abcdefghijklm", 2, 4).equals("cgk"));
        System.out.println(sliceString("abcdefghijklm", 3, 4).equals("dhl"));
        System.out.println(sliceString("abcdefghijklm", 0, 5).equals("afk"));
        System.out.println(sliceString("abcdefghijklm", 1, 5).equals("bgl"));
        System.out.println(sliceString("abcdefghijklm", 2, 5).equals("chm"));
        System.out.println(sliceString("abcdefghijklm", 3, 5).equals("di"));
        System.out.println(sliceString("abcdefghijklm", 4, 5).equals("ej"));
    };

    
    public int[] tryKeyLength(String encrypted, int len, char mostCommon) {
        int[] key = new int[len];
        for (int i = 0; i < len; i++) {
            String sliced = sliceString(encrypted, i, len);
            CaesarCracker cc = new CaesarCracker(mostCommon);
            key[i] = cc.getKey(sliced);
        }
        return key;
    }
    
    public void testTryKeyLength() {
        FileResource fr = new FileResource("messages/secretmessage2.txt");
        String message = fr.asString();
        //int klength = "flute".length();
        int[] key = tryKeyLength(message, 38, 'e');
        System.out.println("Number of words " +message.length());
    }
    
    /**
     * Put everything together, so that you can create a new VigenereBreaker in BlueJ, 
     * call this method on it, and crack the cipher used on a message.
     */
    public void breakVigenere() {
        FileResource dictFile = new FileResource("dictionaries/English");
        HashSet<String> dictionary = readDictionary(dictFile);
        //System.out.println(dictionary);
        FileResource fr = new FileResource("messages/secretmessage2.txt");
        String message = fr.asString();
        String decrypted = breakForLanguage(message, dictionary);
        //String encrypted = new FileResource().asString();
        HashMap<String, HashSet<String>> dictionaries = loadDictionaries();
        breakForAllLangs(message, dictionaries);
        System.out.println(" ");
        //System.out.println(decrypted);
    }
    
    
    public HashSet<String> readDictionary(FileResource fr) {
        // Make a new HashSet of Strings
        HashSet<String> dictionaryWords = new HashSet<String>();
        // Read each line in fr (which should contain exactly one word per line)
        for (String line : fr.lines()) {
            // Convert line to lowercase
            line = line.toLowerCase();
            // Put the line into the HashSet
            dictionaryWords.add(line);
        }
        return dictionaryWords;
    }
    
    
    public int countWords(String message, HashSet<String> dictionary) {
        int realWords = 0;
        // Split the message into words
        String[] words = message.split("\\W+");
        // Iterate over those words
        for (String word : words) {
            word = word.toLowerCase();
            if (dictionary.contains(word)) {
                realWords++;
            }
        }
        return realWords;
    }
    
    private HashMap<String, HashSet<String>> loadDictionaries() {
        String[] langs = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
        HashMap<String, HashSet<String>> dictionaries = new HashMap<String, HashSet<String>>();
        for (String lang : langs) {
            dictionaries.put(lang, readDictionary(new FileResource("dictionaries/"+lang)));
        }
        return dictionaries;
    }   
    
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int maxRealWords = 0; 
        int finalKeyLength = 1;
        String decryptionWithMostRealWords = "";
        System.out.println("Checking for keys....");
        System.out.println(" ");
        for (int keylength = 1; keylength <= 100; keylength++) {
            // Decrypt the message
            int[] key = tryKeyLength(encrypted, keylength, 'e');
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            // Count how many real words decrypted message contains
            int realWords = countWords(decrypted, dictionary);
            if (realWords > maxRealWords) {
                System.out.println("Key length considered: "+keylength);
                maxRealWords = realWords;
                decryptionWithMostRealWords = decrypted;
                finalKeyLength = keylength;
            }
            /*if (keylength==38)
                break;*/
        }
        System.out.println("Message contains " + maxRealWords + " valid words");
        System.out.println("Message decoded with keylength of " + finalKeyLength);
        return decryptionWithMostRealWords;
    }
    
    public void breakForAllLangs (String encrypted, HashMap<String, HashSet<String>> languages) {
        if (encrypted == null || encrypted.isEmpty()) return;
        if (languages == null || languages.isEmpty()) return;

        int bestCount = 0;
        String bestLanguage = "";
        String bestDecrypt = "";
        for (String lang : languages.keySet()) {
            HashSet<String> dictionary = languages.get(lang);
            String decrypted = breakForLanguage(encrypted, dictionary);
            int count = countWords(decrypted, dictionary);
            if (count > bestCount) {
                bestCount = count;
                bestLanguage = lang;
                bestDecrypt = decrypted;
            }
        }

        System.out.println("Message in "+bestLanguage+":");
        System.out.println(bestDecrypt);
        System.out.println("----END----\n");
    }  // breakForAllLangs
}