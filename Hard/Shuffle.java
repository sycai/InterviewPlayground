package Hard;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by sycai on 7/4/2017.
 * CCI 17.2 Write a method to shuffle an array. It must be a perfect shuffle -- in other words, each of the n!
 * permutations of the array elements has to be equally likely, where n is the length of the array. Assume that you are
 * given a random number generator which is perfect.
 */
public class Shuffle {
    private static Random rand = new Random();

    public static void shuffle(int[] A) {
        if (A == null || A.length < 2)  return;
        for (int i = 0; i < A.length; i++) {
            int randIdx = rand.nextInt(i+1);
            int temp  = A[i];
            A[i] = A[randIdx];
            A[randIdx] = temp;
        }
    }

    public static void main(String[] args) {
        int[] A = {1,2,3,4,5,6,7,8,9,10};
        for (int i = 0 ; i < 100; i++) {
            shuffle(A);
            System.out.println(Arrays.toString(A));
        }
    }
}
