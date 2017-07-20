package Hard;

import java.util.Arrays;
import java.util.HashSet;

/**
 * CCI 17.15 Given a list of words, write a program to find the longest word made of other words in the list.
 *
 * EXAMPLE
 * Input: cat, banana, dog, nana, walk, walker, dogwalker
 * Output: dogwalker
 */

public class LongestWord {
    private String[] sorted;
    private HashSet<String> bagOfWords;

    public LongestWord(String[] w) {
        sorted = Arrays.copyOf(w, w.length);
        Arrays.sort(sorted, ((o1, o2) -> o2.length() - o1.length())); // Longest word comes first
        bagOfWords = new HashSet<>(Arrays.asList(sorted));
    }

    public String find() {
       for (String s : sorted) {
           if (isComposed(s, false))   return s;
       }
       return null;
    }

    private boolean isComposed(String s, boolean truncated) {
        if (bagOfWords.contains(s) && truncated) {
            return true;
        }
        int upperLimit = truncated ? s.length() : s.length() - 1;
        for (int i = 1; i <= upperLimit; i++) {
            String prefix = s.substring(0,i);
            if (bagOfWords.contains(prefix)) {
               if (isComposed(s.substring(i), true)) {
                   return true;
               }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String[] words = {"cat", "banana", "dog", "nana", "walk", "walker", "dowalker", "ba"};
        LongestWord lw = new LongestWord(words);
        System.out.println(lw.find());
    }
}
