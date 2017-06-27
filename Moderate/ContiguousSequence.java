package Moderate;

/**
 * Created by sycai on 6/26/2017.
 * CCI 16.17 You are given an array of integers (both positive and negative). Find the contiguous sequence with the
 * largest sum. Return the sum.
 *
 * EXAMPLE
 * Input: 2, -8, 3, -2, 4, -10
 * Output: 5(i.e., {3,-2,4})
 */
public class ContiguousSequence {
    public static int maxContiguousSum(int[] A) {
        if (A == null || A.length == 0) return 0;
        int curr = 0;
        int max = Integer.MIN_VALUE;
        for (int a : A) {
            curr = Math.max(curr + a, a);
            max = Math.max(curr, max);
        }
        return Math.max(0, max);
    }

    public static void main(String[] args) {
        int[] A = {2, -8, 3, -2, 4, -10};
        System.out.println(maxContiguousSum(A));
    }
}
