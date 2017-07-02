package Moderate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sycai on 7/2/2017.
 * CCI 16.24 Design an algorithm to find all pairs of integers within an array which sum to a specified value.
 */
public class PairsWithSum {
    public static Set<List<Integer>> findPairs(int[] A, int target) {
        Set<List<Integer>> res = new HashSet<>();
        if (A == null)  return res;

        Arrays.sort(A);
        int first = 0, last = A.length - 1;
        while (first < last) {
            int sum = A[first] + A[last];
            if (sum == target)  {
                res.add(Arrays.asList(A[first], A[last]));
                first++;
            }
            else if (sum < target) {
                first++;
            } else {
                last--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] A = {5,7,-1,3,-2,10,6,-9};
        System.out.println(findPairs(A, 4));
    }
}
