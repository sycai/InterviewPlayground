package Hard;

/**
 * CCI 17.24 Given an N*N matrix of positive and negative integers, write code to find the submatrix with the largest
 * possible sum.
 */

public class MaxSubmatrix {
    private int[][] M;
    private int[][] S; // S[i][j] = M[0][j] + M[1][j] + ... + M[i][j]
    private int mRow;
    private int mCol;

    public MaxSubmatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            throw new IllegalArgumentException("There is no element in the given matrix");
        }
        M = matrix;
        mRow = matrix.length;
        mCol = matrix[0].length;

        S = new int[mRow][mCol];
        for (int c = 0; c < mCol; c++) {
            int sumSoFar = 0;
            for (int r = 0; r < mRow; r++) {
                sumSoFar += M[r][c];
                S[r][c] = sumSoFar;
            }
        }
    }

    public int maxSubMatrixSum() {
        int max = Integer.MIN_VALUE;
        for (int r1 = 0; r1 < mRow; r1++) {
            for (int r2 = r1; r2 < mRow; r2++) {
                max = Math.max(max, maxSubMatrixSumBetweenRow(r1, r2));
            }
        }
        return max;
    }

    private int maxSubMatrixSumBetweenRow(int lo, int hi) {
        int maxOfAll = S[hi][0] - S[lo][0] + M[lo][0];
        int prevMax = maxOfAll;
        for (int col = 1; col < mCol; col++) {
            int currColSum = S[hi][col] - S[lo][col] + M[lo][col];
            int currMax = Math.max(currColSum, prevMax + currColSum);
            maxOfAll = Math.max(maxOfAll, currMax);
            prevMax = currMax;
        }
        return maxOfAll;
    }

    public static void main(String[] args) {
        int[][] M = {
                {-1,3,-2,6,-5},
                {-5,2,-2,6,-8}
        };
        MaxSubmatrix ms = new MaxSubmatrix(M);
        System.out.println(ms.maxSubMatrixSum());
    }
}
