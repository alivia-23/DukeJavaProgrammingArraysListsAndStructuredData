
/**
 * Write a description of class WordPlay here.
 *
 * @author (Alivia Guin)
 * @version (a version number or a date)
 */
import edu.duke.*;
public class WordPlay
{
    public boolean isVowel(char ch) {
        String vowels = "aeiou";
        for (int i = 0; i < vowels.length(); i++) {
            // default lower case letters
            char c = vowels.charAt(i);
            // convert to uppercase letter
            char upC = Character.toUpperCase(c);
            // if the character is lowercase vowel
            if (ch == c) {
                return true;
            } else if (ch == upC) { // if the character is uppercase vowel
                return true;
            } 
                        
        }
        return false;
    }
    
    public void testIsVowel() {
        //char c = 'F';
        //char c = 'f';
        //char c = 'a';
        char c = 'A';
        System.out.println(isVowel(c)+ " Alphabet is "+c);
    }
    
    private boolean hasValue (String s) { 
        return s != null && ! s.isEmpty(); 
    }
    
    public String replaceVowels(String phrase, char ch) {
        if (!hasValue(phrase)) return "";
        
        StringBuilder replacedPhrase = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++) {
            char c = replacedPhrase.charAt(i);
            if (isVowel(c)) {
                replacedPhrase.setCharAt(i, ch);
            }
        }
        return replacedPhrase.toString();
    }
    
    public void testReplaceVowels() {
        String word = "Hello World";
        char ch = '*';
        System.out.println(replaceVowels(word, ch));
    }
    
    private char toLower(char ch) { 
        return Character.toLowerCase(ch); 
    }
    
    private char toUpper(char ch) { 
        return Character.toUpperCase(ch); 
    }
    
    private char replaceChar(int pos) { 
        return (pos % 2 == 0) ? '*' : '+'; 
    }
    
    public String emphasize(String phrase, char ch) {
        if (!hasValue(phrase)) return "";
        StringBuilder newPhrase = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++) {
            char c = newPhrase.charAt(i);
            if (toLower(c) == toLower(ch) || toUpper(c) == toUpper(ch)) {
                newPhrase.setCharAt(i, replaceChar(i));
            }
        }
        return newPhrase.toString();
    }
    
    public void testEmphasize() {
        //String phrase = "dna ctgaaactga";
        //String phrase = "Mary Bella Abracadabra";
        String phrase = "This Is A Demo test";
        char ch = 't';
        System.out.println(emphasize(phrase, ch));
    }
    
}
