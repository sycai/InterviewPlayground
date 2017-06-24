package Moderate;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by sycai on 6/24/2017.
 * CCI 16.16 Given an array of integers, write a method to find indices m and n such that if you sorted elements m
 * through n, the entire array would be sorted. Minimize n - m (that is, find the smallest such sequence);
 */
public class SubSort {
    public static void findSubSortPair(int[] A) {
        if (A == null || A.length == 0) {
            System.out.println("The given array is empty");
            return;
        }

        Deque<Integer> stack = new LinkedList<>();
        int m = A.length, n = 0;
        // max is the maximum value in the unsorted array segment.
        int max = A[0];
        for (int idx = 0; idx < A.length; idx++) {
            int a = A[idx];
            if (a < max) n = idx;

            if (stack.isEmpty() || stack.peek() <= a)  {
                stack.push(a);
            } else {
                max = stack.peek() > max ? stack.peek() : max;
                while (!stack.isEmpty() && stack.peek() > a) {
                    stack.pop();
                }
                m = stack.size() < m ? stack.size() : m;
                stack.push(a);
            }
        }
        if (m >= n)     System.out.println("Array is already sorted");
        else            System.out.println(String.format("m = %d  n = %d", m, n));
    }

    public static void main(String[] args) {
        int[] A = {};
        findSubSortPair(A);
    }
}
