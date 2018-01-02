package Strings;
/**
 * Knuth-Morris-Pratt substring search explained by DFA simulation.
 *
 * Suppose we have pattern "pat" = "ABABAC" and text "txt".
 *
 * There are 7 states for "pat", represented by integers 0 - 6, where 0
 * stands for the start and 6 stands for the end. A path that leads to
 * the "match" transition is depicted as follows:
 *
 *     A        B        A        B        A       C
 * 0  --->  1  --->  2  --->  3  --->  4  ---> 5  ---> 6
 *
 * A two-dimentional array "dfs" can fully represent such automaton:
 *
 * If we are at state S and the first unmatched character from text is C, the next
 * state is dfs[C][S]
 */
public class KMP {
    private String pat;
    // "Deterministic Finite-state Automaton"
    private int[][] dfa;

    public KMP(String pat) {
        // Build DFA from pattern
        this.pat = pat;
        int M = pat.length();
        int R = 256;
        dfa = new int[R][M];
        // First match makes dfa jumps to the next state
        dfa[pat.charAt(0)][0] = 1;
        for (int X = 0, j = 1; j < M; j++) {
            // Compute dfs[][j]
            for (int c = 0; c < R; c++) {
                dfa[c][j] = dfa[c][X];
            }
            // A character match makes dfa jumps to the next state
            dfa[pat.charAt(j)][j] = j+1;
            X = dfa[pat.charAt(j)][X];
        }
    }

    public int search(String txt) {
        int i, j, N = txt.length(), M = pat.length();
        for (i = 0, j = 0; i < N && j < M; i++) {
            j = dfa[txt.charAt(i)][j];
        }
        if (j == M) {
            return i - M;
        } else {
            return N;
        }
    }
}