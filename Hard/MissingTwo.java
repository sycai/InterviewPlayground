package Hard;

import java.util.Arrays;

/**
 * CCI 17.19 You are given an array with all the numbers from 1 to N appearing exactly once, except for one number that
 * is missing. How can you find the missing number in O(N) time and O(1) space? What if there were two numbers missing?
 */

public class MissingTwo {
    public static int[] findMissingTwo(int[] A, int N) {
        int[] res = new int[2];
        int sum = 0, sqSum = 0;
        for (int a : A) {
            sum += a;
            sqSum += a * a;
        }

        int s1 = N*(N + 1)/2 - sum; // s1 is the sum of missing two
        int s2 = N*(N + 1)*(2*N + 1)/6 - sqSum; // s2 is the sum of squares of missing two

        double a = (s1 + Math.sqrt(2*s2 - s1*s1))/2;
        double b = s1 - a;

        res[0] = (int) Math.round(a);
        res[1] = (int) Math.round(b);
        return res;
    }

    public static void main(String[] args) {
        int[] A = {5,2,3,8,7,6};
        System.out.println(Arrays.toString(findMissingTwo(A, 8)));
    }
}
