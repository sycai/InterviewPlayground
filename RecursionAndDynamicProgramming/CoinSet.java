package RecursionAndDynamicProgramming;

/**
 * Created by sycai on 3/22/2017.
 * You are given a set of N coins with distinct values. You are also given a target value V.
 * The goal is to tell whether it is possible to find a subset of coins that has the total value of V.
 * (Each coin can appear only once in this set.)
 */
public class CoinSet {
    private boolean[][] P;

    public CoinSet(int[] coins, int V) {
        P = new boolean[coins.length][V + 1];
        /*
        for (int i = 0; i < coins.length; i++) {
            for (int v = 0; v <= V; v++) {
                if (v == 0)             P[i][v] = true;
                else if (i == 0)        P[i][v] = false;
                else if (v >= coins[i]) P[i][v] = P[i-1][v] || P[i-1][v-coins[i]];
                else                    P[i][v] = P[i-1][v];
            }
        }*/

        for (int i = 0; i < P.length; i++) {
            P[i][0] = true;
        }

        for (int w = 1; w < P[0].length; w++) {
            P[0][w] = false;
        }

        for (int i = 1; i < P.length; i++) {
            for (int W = 1; W < P[0].length; W++) {
                if (coins[i] <=
                        W) {
                    P[i][W] = P[i-1][W-coins[i]] || P[i-1][W];
                } else {
                    P[i][W] = P[i-1][W];
                }
            }
        }

        // Table printing
        System.out.print("v= \t\t");
        for (int i= 0; i < P[0].length; i++)
            System.out.print(i+"\t\t");
        System.out.println();
        for (int i = 0; i < P.length; i++) {
            System.out.print("i=" + i + ":\t");
            for (int j = 0; j <P[0].length; j++) {
                System.out.print(P[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] coins = {0,5,1,2};
        CoinSet cs = new CoinSet(coins, 8);
    }
}
