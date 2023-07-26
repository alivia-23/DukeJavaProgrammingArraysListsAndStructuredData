
/**
 * Write a description of class IdentifyCharacter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class IdentifyCharacter
{
   public void digitTest() {
       String test = "ABCabc0123456789';#!";
       for (int i = 0; i < test.length(); i++) {
           char ch = test.charAt(i);
           if (Character.isDigit(ch)) {
               System.out.println(ch+ " is a digit");
           } 
           if (Character.isAlphabetic(ch)) {
               System.out.println(ch+ " is an Alphabet");
           }
           if (ch == '#') {
               System.out.println(ch+ " is a #hashtag");
           }
       }
   }
 
   public void conversionTest() {
       String test = "ABCDEFabcdef123!#";
       for (int i = 0; i < test.length(); i++) {
           char ch = test.charAt(i);
           char uch = Character.toUpperCase(ch);
           char lch = Character.toLowerCase(ch);
           System.out.println(ch+" "+uch+ " " +lch);
       }
   }
}
