package Moderate;

import java.util.Arrays;

/**
 * Created by sycai on 6/15/2017.
 * CCI 16.6 Given two arrays of integers, compute the pair of values (one value in each array) with the smallest
 * (non-negative) difference. Return the difference.
 *
 * EXAMPLE
 * Input: {1, 3, 15, 11, 2}, {23, 127, 235, 19, 8}
 * Output: 3. That is, the pair (11, 8)
 *
 */
public class SmallestDifference {

    public static int smallestDiff(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);

        int i = 0, j = 0;
        int minDiff = Integer.MAX_VALUE;

        while (i < A.length && j < B.length ) {
            minDiff = Math.min(Math.abs(A[i] - B[j]), minDiff);
            if (A[i] < B[j])    i++;
            else                j++;
        }
        return minDiff;
    }

    public static void main(String[] args) {
        int[] A = {1, 3, 15, 11, 12}, B = {23, 127, 235, 19, 9};
        System.out.println(smallestDiff(A, B));
    }
}
