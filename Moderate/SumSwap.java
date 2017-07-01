package Moderate;

import java.util.Arrays;

/**
 * Created by sycai on 6/30/2017.
 * CCI 16.21 Given two arrays of integers, find a pair of values (one value from each array) that you can swap to
 * give the two arrays the same sum.
 *
 * EXAMPLE
 * Input: {4,1,2,1,1,2} and {3,6,3,3}
 * Output: {1,3}
 */
public class SumSwap {
    public static int[] findTwoElem(int[] A, int[] B) {
        if (A == null || B == null) return null;
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();
        int diff = sumA - sumB;
        if (diff % 2 == 1)  return null;

        Arrays.sort(A);
        Arrays.sort(B);

        int i = 0, j = 0;
        while (i < A.length && j < B.length) {
            int d = A[i] - B[j];
            if (d == diff / 2)      return new int[]{A[i], B[j]};
            else if (d < diff / 2)  i++;
            else                    j++;
        }
        return null;
    }

    public static void main(String[] args) {
        int[] A = {};
        int[] B = {};
        System.out.println(Arrays.toString(findTwoElem(A,B)));
    }
}
