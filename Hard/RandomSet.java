package Hard;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by sycai on 7/4/2017.
 * CCI 17.3 Write a method to randomly generate a set of m integers from an array of size n. Each element must have
 * equal probability of being chosen
 */
public class RandomSet {
    private static Random rand = new Random();

    public static Set<Integer> randomSet(int[] A, int m) {
        if (A == null || m > A.length ) return null;
        Set<Integer> res = new HashSet<>();
        for (int i = 0; i < m; i++) {
            int idx = rand.nextInt(A.length - i);
            res.add(A[idx]);
            swap(A, idx, A.length - i - 1);
        }
        return res;
    }

    private static void swap(int[] A, int x, int y) {
        if (x == y) return;
        int temp = A[x];
        A[x] = A[y];
        A[y] = temp;
    }

    // This method is provided in the book
    public static int[] randomSet2(int[] A, int m) {
        if (A == null || m > A.length)  return null;
        int[] subset = Arrays.copyOf(A, m);
        for (int i = m; i < A.length; i++) {
            int randIdx = rand.nextInt(i+1);
            if (randIdx < m)  subset[randIdx] = A[i];
        }
        return subset;
    }

    public static void main(String[] args) {
        int[] A = {0,1,2,3,4,5,6,7,8,9};
        int[] count = new int[A.length];
        for (int i = 0; i < 100000; i++) {
            Set<Integer> s = randomSet(A, 4);
            for (int e : s) {
                count[e]++;
            }
        }
        System.out.println(Arrays.toString(count));

        Arrays.fill(count, 0);
        for (int i = 0; i < 100000; i++) {
            int[] s = randomSet2(A, 4);
            for (int e : s) {
                count[e]++;
            }
        }
        System.out.println(Arrays.toString(count));
    }
}
