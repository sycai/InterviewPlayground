package SortingAndSearching;

import java.util.Arrays;

/**
 * Created by sycai on 5/17/2017.
 * CCI 10.1 You are given two sorted arrays, A and B, where A has a large enough buffer at the end to hold B. Write a
 * method to merge B into A in sorted order.
 */

public class SortedMerge {
    public static void merge(int[] A, int end, int[] B) {
        // Argument "end" is the idx of the last element in A
        if (end + 1 + B.length > A.length) return; // Overflow!
        // Start merging from the tail of A
        int i = end, j = B.length - 1;
        int pos = i + j + 1;
        while (i != -1 && j != -1) {
            if (A[i] <= B[j])    A[pos--] = B[j--];
            else                 A[pos--] = A[i--];
        }
        if (i == -1) {// The remaining elements in B should be put in the head of A
            while (j != -1) A[pos--] = B[j--];
        }
    }

    public static void main(String[] args) {
        int[] A = new int[10];
        A[0] = 1;
        A[1] = 3;
        A[2] = 5;
        int[] B = {2,4,6,8};
        merge(A, 2, B);
        System.out.println(Arrays.toString(A));
    }
}
