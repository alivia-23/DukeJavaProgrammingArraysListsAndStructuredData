
/**
 * Write a description of class WordLengths here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import edu.duke.*;

public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts) {
       int lastIndex = counts.length - 1;
        for (String word : resource.words()) {
            // Apparently there is a defect in FileResource.words(): using an
            // empty file will return a "word" of zero length, so we have to
            // test for that.  In my opinion, it should return nothing, so the
            // foreach loop does not execute at all.  Not checking for this
            // means an exception below on word.charAt(0).
            if (word.isEmpty()) break;

            // Since we are tracking length of word instead of the actual
            // words, we don't need to adjust the string itself if the
            // first or last characters are not letters.
            int wordLen = word.length();    // used to avoid multiple calls to length()
            int len = wordLen;
            if (!Character.isLetter(word.charAt(0))) len -= 1;
            if (!Character.isLetter(word.charAt(wordLen-1))) len -= 1;

            // Now figure out where to put `len`.  Remember if len > last spot in
            // `count`, we bump the final spot in `counts`.  It is also possible
            // that `len` is <= 0, if the word is small and there are no letters in it!
            // And a word must have a non-zero length at this point to be counted.
            if (len > lastIndex)
                counts[lastIndex]++;
            else if (len > 0)
                counts[len]++;
        }
    }

    /** @return the index position of the largest element in `values`.
     * @param values    the data to examine for the largest element
     */
    public int indexOfMax (int[] values) {
        if (values.length == 0) return -1;

        int maxIdx = 0;
        for (int k = 0; k < values.length; k++)
            if (values[k] > values[maxIdx])
                maxIdx = k;
        return maxIdx;
    } 
    
    public void testCountWordLengths() {
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        
        for (int i = 0; i < counts.length; i++) {
            System.out.println("Length " + i + " = " + counts[i] + " words");
        }
        
        System.out.println("Most common length = " + indexOfMax(counts));
    }
}
