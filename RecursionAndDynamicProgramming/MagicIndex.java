package RecursionAndDynamicProgramming;

/**
 * Created by sycai on 4/29/2017.
 * CCI 8.3 A magic index in an array A[0 ... n-1] is defined to be an index such that A[i] = i. Given a sorted array of
 * distinct integers, write a method to find a magic index, if one exists, in array A.
 *
 * FOLLOW UP
 * What if the values are not distinct?
 */

public class MagicIndex {
    public static void main(String[] args) {
        int[] A = {1,1,1,2,5};
        System.out.println(findMagicDup(A,0,A.length-1));
    }

    public static int findMagic(int[] A, int lo, int hi) {
        // This algorithm works on the assumption that there are no duplicate numbers in A.
        if (hi < lo)    return -1; // Failed search

        int mid = lo + (hi - lo) / 2;

        if      (A[mid] == mid) return mid;
        else if (A[mid] > mid)  return findMagic(A, lo, mid-1);
        else                    return findMagic(A, mid+1, hi);
    }

    public static int findMagicDup(int[] A, int lo ,int hi) {
        // This algorithm works on the assumption that there can be duplicate numbers in A.
        if (hi < lo)    return -1;

        int mid = lo + (hi - lo) /2;
        if      (A[mid] == mid) return mid;

        // if there are many magic numbers, return the smallest one.
        int leftRes = findMagicDup(A, lo, Math.min(mid-1, A[mid]));
        if (leftRes > 0) {
            return leftRes;
        }

        int rightRes = findMagicDup(A, Math.max(mid+1, A[mid]), hi);
        return rightRes;
    }
}
