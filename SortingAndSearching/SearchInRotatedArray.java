package SortingAndSearching;

/**
 * Created by sycai on 5/17/2017.
 * CCI 10.3 Given a sorted array of n integers that has been rotated an unknown number of times, write code to find
 * an element in the array. You may assume that the array was originally sorted in increasing order.
 *
 * EXAMPLE
 * Input: find 5 in {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14}
 * Output: 8 (the index of 5 in the array)
 */
public class SearchInRotatedArray {
    public static int search(int[] A, int val) {
        if (A.length == 0 || A == null) return -1;
        return search(A, val, 0, A.length - 1);
    }

    private static int search(int[] A , int val, int lo, int hi) {
        if (lo == hi) {
            if (A[lo] == val)   return lo;
            else                return -1;
        }

        int mid = lo + (hi - lo) / 2;
        if (A[lo] <= A[mid]) { // The left half is sorted
            if (A[lo] <= val && val <= A[mid])      return search(A, val, lo, mid);
            else                                    return search(A, val, mid + 1, hi);
        } else { // The right half is sorted
            if (A[mid+1] <= val && val <= A[hi])    return search(A, val, mid + 1, hi);
            else                                    return search(A, val, lo, mid);
        }
    }

    public static void main(String[] args) {
        int[] A = {2,2,2,3,4,1};
        int result = SearchInRotatedArray.search(A, 1);
        System.out.println(result);
    }
}
