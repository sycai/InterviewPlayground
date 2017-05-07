package RecursionAndDynamicProgramming;

/**
 * Created by sycai on 5/7/2017.
 * Given two strings, we want to find the length of their maximum common substring. This substring must be contiguous
 * in both input strings (for example, "abcde" is a substring of "cdabcdefg", but not of "cdabccdefg"). Design a
 * dynamic programming algorithm for finding this length.
 */
public class MaxCommonSubstring {
    private int[][] A;
    private int MCS;

    MaxCommonSubstring(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) {
            MCS = 0;
        } else {
            A = new int[s1.length()][s2.length()];
            findMCS(s1, s2);
        }
    }

    private void findMCS(String s1, String s2) {
        // Initialization
        for (int i = 0; i < s1.length(); i++) {
            A[i][0] = (s1.charAt(i) == s2.charAt(0)) ? 1 : 0;
        }
        for (int j = 0; j < s2.length(); j++) {
            A[0][j] = (s1.charAt(0) == s2.charAt(j)) ? 1 : 0;
        }

        int maxSoFar = Integer.MIN_VALUE;
        // A[i][j] is the length of the max common substring ending at s1[i] as well as s2[j].
        // Therefore, if s1[i] != s2[j], such common substring does not exist.
        // If s1[i] == s2[j], A[i][j] = A[i-1][j-1] + 1.
        for (int i = 1; i < s1.length(); i++) {
            for (int j = 1; j < s2.length(); j++) {
                A[i][j] = (s1.charAt(i) == s2.charAt(j)) ? A[i-1][j-1] + 1 : 0;
                maxSoFar = (maxSoFar > A[i][j]) ? maxSoFar : A[i][j];
            }
        }
        // The max length of common substring is the max value in A.
        MCS = maxSoFar;
    }

    public int getMCS() { return MCS; }

    public static void main(String[] args) {
        String s1 = "cce";
        String s2 = "cdabccdefg";

        MaxCommonSubstring mcs = new MaxCommonSubstring(s1, s2);
        System.out.println(mcs.getMCS());
    }
}
