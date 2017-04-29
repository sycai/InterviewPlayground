package RecursionAndDynamicProgramming;

/**
 * Created by sycai on 3/24/2017.
 * Find the shortest edit distance between two strings.
 */
public class MinEditDistance {
    public int answer;
    public MinEditDistance(String s1, String s2) {
        int[][] E = new int[s1.length()+1][s2.length()+1];

        // Initialization
        for (int i = 0; i <= s1.length(); i++) {
            E[i][0] = i;
        }

        for (int j = 0; j <= s2.length(); j++) {
            E[0][j] = j;
        }

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                char c1 = s1.charAt(i-1), c2 = s2.charAt(j-1);
                E[i][j] = minOfThree(E[i-1][j] + 1, E[i][j-1] + 1, E[i-1][j-1] + diff(c1, c2));
            }
        }
        answer = E[s1.length()][s2.length()];

        // Printing the table
        System.out.print("\t\t");
        for (char c : s2.toCharArray()) {
            System.out.print(c + "\t");
        }
        System.out.println();
        for (int i = 0; i <= s1.length(); i++) {
            if (i == 0)  System.out.print("\t");
            else         System.out.print(s1.charAt(i-1)+ "\t" );
            for (int j = 0; j <= s2.length(); j++) {
                System.out.print(E[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private int diff(char c1, char c2) {
        if (c1 == c2)   return 0;
        else            return 1;
    }

    private int minOfThree(int a, int b, int c) {
        int min = a;
        if (b < min)  min = b;
        if (c < min)  min = c;
        return min;
    }

    public static void main(String[] args) {
        MinEditDistance med = new MinEditDistance("EXPONENTIAL", "POLYNOMIAL");
        System.out.println("The minimum edit distance is: " + med.answer);
    }

}
