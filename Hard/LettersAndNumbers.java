package Hard;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by sycai on 7/7/2017.
 * CCI 17.5 Given an array filled with letters and numbers, find the longest subarray with an equal number of letters
 * and numbers.
 */
public class LettersAndNumbers {
    public static int[] longestSubarray(char[] A) {
        if (A == null)  return null;
        HashMap<Integer, Integer> diffToIdx = new HashMap<>();
        diffToIdx.put(0,0);
        int diff = 0; // #letters - #numbers
        int[] startEndPair = new int[2];
        int maxLen = 0;
        for (int i = 0; i < A.length; i++) {
            char c = A[i];
            if      (Character.isLetter(c))     diff++;
            else if (Character.isDigit(c))      diff--;
            else    throw new IllegalArgumentException("Invalid character appears in the array");

            if (diffToIdx.containsKey(diff)) {
                int currLen = i - diffToIdx.get(diff) + 1;
                if (maxLen < currLen) {
                    startEndPair[0] = diffToIdx.get(diff);
                    startEndPair[1] = i;
                    maxLen = currLen;
                }
            } else {
                diffToIdx.put(diff, i+1);
            }
        }
        return startEndPair;
    }

    public static void main(String[] args) {
        String A = "abc123abc1234";
        System.out.println(Arrays.toString(longestSubarray(A.toCharArray())));
    }
}
