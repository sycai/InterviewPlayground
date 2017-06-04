package SortingAndSearching;

import java.util.Arrays;

/**
 * Created by sycai on 6/3/2017.
 * CCI 10.11 In an array of integers, a "peak" is an element which is greater than or equal to the adjacent integers
 * and a "valley" is an element which is less than or equal to the adjacent integers. For example, in the array
 * {5,8,6,2,3,4,6}, {8,6} are peaks and {5,2} are valleys. Given an array of integers, sort the array into an
 * alternating sequence of peaks and valleys.
 *
 * EXAMPLE
 * Input: {5,3,1,2,3}
 * Output: {5,1,3,2,3}
 */


public class PeaksAndValleys {
    public static int[] alternatingSort(int[] A) {
        // O(n log n) complexity
        int[] sortedA = Arrays.copyOfRange(A, 0, A.length);
        Arrays.sort(sortedA);
        int[] res = new int[A.length];
        int idx = 0;
        for (int i = 0 , j = A.length - 1; i <= j; i++, j-- ) {
            res[idx++] = sortedA[j];
            if (idx >= A.length)    break;
            res[idx++] = sortedA[i];
        }
        return res;
    }

    public static int[] optimalAlternatingSort(int[] A) {
        // O(n) complexity
        int[] res = Arrays.copyOfRange(A, 0, A.length);
        if (A.length > 1) {
            for (int idx = 1; idx < res.length; idx += 2) {
                // Swap the current element with the largest adjacent element
                if (res[idx] <= res[idx - 1] && res[idx] <= res[idx + 1]) continue;
                if (res[idx - 1] <= res[idx] && res[idx - 1] <= res[idx + 1]) swap(res, idx - 1, idx);
                if (res[idx + 1] <= res[idx] && res[idx + 1] <= res[idx - 1]) swap(res, idx + 1, idx);
            }
        }
        return res;

    }

    private static void swap(int[] A, int i, int j) {
        if (i == j)  return;
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public static void main(String[] args) {
        int[] A = {1};
        System.out.println(Arrays.toString(PeaksAndValleys.alternatingSort(A)));
        System.out.println(Arrays.toString(PeaksAndValleys.optimalAlternatingSort(A)));
    }
}
