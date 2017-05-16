package RecursionAndDynamicProgramming;

/**
 * Created by sycai on 5/15/2017.
 * CCI 8.14 Given a boolean expression consisting of the symbols 0 (false), 1 (true), & (AND), | (OR), and ^ (XOR), and
 * a desired boolean result value "result", implement a function to count the number of ways of parenthesizing the
 * expression such that it evaluates to "result". The expression should be fully parenthesized (e.g., (0)^(1) ) but not
 * extraneously (e.g., ((0))^(1) ).
 *
 * EXAMPLE
 * countEval("1^0|0|1", false) -> 2
 * countEval("0&0&0&1^1|0", true) -> 10
 */


public class CountEval {
    private int[][][] C;
    private int N; // Number of symbols
    private boolean result;

    public CountEval(String s, boolean r) {
        N = s.length()/2 + 1;
        C = new int[N][N][2]; // C[i][j][b] = # ways of evaluating symbol[i..j] to boolean value b
        // Initialization
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C.length; j++) {
                C[i][j][0] = C[i][j][1] = -1; // An unvisited entry is marked by -1
            }
        }
        result = r;
        buildC(s, 0, C.length - 1);
    }

    private void buildC(String s, int i, int j) {
        if (i == j) { // A single symbol
            C[i][j][0] = '1' - s.charAt(2*i);
            C[i][j][1] = s.charAt(2*i) - '0';
        } else { // An expression
            C[i][j][0] = C[i][j][1] = 0; // Prepare for the self increment in the loop body
            for (int k = i; k < j; k++) { // Each operator can have parenthesized clauses on both sides
                char operator = s.charAt(k * 2 + 1);

                if (C[i][k][0] == -1 || C[i][k][1] == -1)       buildC(s, i, k);
                if (C[k+1][j][0] == -1 || C[k+1][j][1] == -1)   buildC(s, k+1, j);

                int leftFalse = C[i][k][0], leftTrue = C[i][k][1];
                int rightFalse = C[k+1][j][0], rightTrue = C[k+1][j][1];

                if (operator == '|') {
                    C[i][j][0] += leftFalse * rightFalse;
                    C[i][j][1] += (leftFalse + leftTrue) * (rightFalse + rightTrue) - leftFalse * rightFalse;
                } else if (operator == '&') {
                    C[i][j][0] += (leftFalse + leftTrue) * (rightFalse + rightTrue) - leftTrue * rightTrue;
                    C[i][j][1] += leftTrue * rightTrue;
                } else if (operator == '^') {
                    C[i][j][0] += leftFalse * rightFalse + leftTrue * rightTrue;
                    C[i][j][1] += leftTrue * rightFalse + leftFalse * rightTrue;
                }

            }
        }
    }

    public int getResult() {
        if (result) {
            return C[0][N-1][1];
        } else {
            return C[0][N-1][0];
        }
    }

    public static void main(String[] args) {
        CountEval ce = new CountEval("0&0&0&1^1|0", true);
        System.out.println(ce.getResult());
    }
}
