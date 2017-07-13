package Hard;

/**
 * Created by sycai on 7/12/2017.
 * CCI 17.10 A majority element is an element that makes up more than half of the items in an array. Given a positive
 * integers array, find the majority element. If there is no majority element, return -1. Do this in O(N) time and
 * O(1) space.
 *
 * EXAMPLE:
 * Input: 1 2 5 9 5 9 5 5 5
 * Output: 5
 */
public class MajorityElement {
    // If number a is the majority in array A, then for any partition P of A, there must exist a subarray in P
    // where a is the majority.
    // In other words, if there exist a partition P of A, for which a is not the majority in any of P's subarrays, then
    // a cannot be the majority in array A.
    // If we find a majority a in a subarray A', a verification step is needed to check whether a is the global
    // majority.
    public static int majorityOf(int[] A) {
        if (A == null || A.length == 0)     return -1;
        int candidate = A[0];
        int candidCount = 0, otherCount = 0;
        for (int i = 0; i < A.length; i++) {
            if (candidCount == 0){
                candidate = A[i];
            }

            if (candidate == A[i])  {
                candidCount++;
            } else {
                otherCount++;
            }

            if (candidCount <= otherCount) {
                candidCount = 0;
                otherCount = 0;
            }
        }

        if (candidCount <= otherCount) {
            return -1;
        }

        int trueCount = 0;
        for (int n : A) {
            if (candidate == n) {
                trueCount++;
            }
        }

        if (2 * trueCount > A.length) {
            return candidate;
        } else {
            return -1;
        }

    }

    public static void main(String[] args) {
        int[] A = {1, 2, 5, 9, 5, 9, 5, 5, 5};
        System.out.println(majorityOf(A));
    }
}
