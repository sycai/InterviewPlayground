package DynamicProgramming;

/**
 * Created by sycai on 4/28/2017.
 * CCI 8.1 A child is running up a staircase with n steps and can hop either 1 step, 2 steps, or 3 steps at a time.
 * Implement a method to count how many possible ways the child can run up the stairs.
 */
public class TripleStep {
    public static void main(String[] args) {
        System.out.println(countWays(4));
    }

    public static int countWays(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        // Let C[n] be the number of ways to run to stair n. C[n] = C[n-1] + C[n-2] + C[n-3]
        int[] C = new int[n+1];
        C[0] = 0;
        C[1] = 1;
        C[2] = 2;

        for (int i = 3; i <= n; i++) {
            C[i] = C[i-1] + C[i-2] + C[i-3];
        }

        return C[n];
    }
}
