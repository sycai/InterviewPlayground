package RecursionAndDynamicProgramming;

/**
 * Created by sycai on 4/2/2017.
 * You are given a set of n coin denominations C_0, C_1, C_2, ... , C_n. There are infinite supplies of coins for each
 * denomination. Find out how many different ways there are to make up amount V.
 */

public class CoinChanging {
    private int[][] N;

    public CoinChanging(int[] C, int V) {
        N = new int[C.length][V + 1];

        // Initialization
        for (int i = 0; i < C.length; i++) {
            N[i][0] = 1;
        }
        for (int v = 1; v <= V; v++) {
            if (v % C[0] == 0)  N[0][v] = 1;
        }
        for (int i = 1; i < C.length; i++) {
            for (int v = 1; v <= V; v++) {
                int n = v / C[i];
                for (int k = 0; k <= n; k++) {
                    //System.out.println("i: " + i + "v: " + v + "k: " + k);
                    N[i][v] += N[i-1][v - k*C[i]];
                }
            }
        }

        System.out.println("#Ways: " + N[C.length-1][V]);
    }

    public static void main(String[] args) {
        int[] coins = {1,5,10};
        CoinChanging cc = new CoinChanging(coins, 20);
    }

}
