package Strings;

/**
 * Note: Strings are indexed from 1 according to CLRS.
 * Please read CLRS for detailed mathematical proof of this algorithm.
 */

public class KMP {
    private String pattern;
    private int[] table;

    public KMP(String pattern) {
        // Compute prefix function
        this.pattern = pattern;
        int len = pattern.length();
        table = new int[len];
        int k = 0;
        for (int q = 1; q < len; q++) {
            while (k > 0 && pattern.charAt(k) != pattern.charAt(q)) {
                k = table[k-1];
            }
            if (pattern.charAt(k) == pattern.charAt(q)) {
                k++;
            }
            table[q] = k;
        }
    }

    public int search(String text) {
        int matchIdx = 0;
        for (int i = 0; i < text.length(); i++) {
            while (matchIdx > 0 && pattern.charAt(matchIdx) != text.charAt(i)) {
                // Not a match, try to find the next longest prefix
                matchIdx = table[matchIdx - 1];
            }
            if (pattern.charAt(matchIdx) == text.charAt(i)) {
                matchIdx++;
            }
            if (matchIdx == pattern.length()) {
                return i - pattern.length() + 1;
            }
        }
        // Not found
        return -1;
    }
}
