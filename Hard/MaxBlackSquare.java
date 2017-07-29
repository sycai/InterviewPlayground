package Hard;

/**
 * CCI 17.23 Imagine you have a square matrix, where each cell (pixel) is either black or white. Design an algorithm
 * to find the maximum subsquare such that all four borders are filled with black pixels.
 */

public class MaxBlackSquare {

    public class SquareCell {
        int blackRight = 0;
        int blackBelow = 0;
        
    }

    private int[][] M;
    private SquareCell[][] C;
    private int size;

    // 1 stands for black and 0 stands for white
    // The given matrix must be a square!
    public MaxBlackSquare(int[][] matrix) {
        M = matrix;
        size = M.length;
        C = new SquareCell[size][size];

        for (int row = size - 1; row >= 0; row--) {
            for (int col = size - 1; col >=0; col--) {
                C[row][col] = new SquareCell();
                if (M[row][col] == 1) {
                    C[row][col].blackRight = (col + 1 == size ? 1 : C[row][col + 1].blackRight + 1);
                    C[row][col].blackBelow = (row + 1 == size ? 1 : C[row + 1][col].blackBelow + 1);
                }
            }
        }
    }

    public int[] findMaxSquare() {
        for (int s = size; s >= 0; s--) {
            for (int r = 0; r < size - s + 1; r++) {
                for (int c = 0; c < size - s + 1; c++) {
                    if (isSquare(r, c, s)) {
                        return new int[] {r, c, s};
                    }
                }
            }
        }
        return new int[3];
    }

    private boolean isSquare(int r, int c, int s) {
        SquareCell upLeft = C[r][c];
        SquareCell belowRight = C[r + s - 1][c + s - 1];
        return upLeft.blackBelow == s &&
                upLeft.blackRight == s &&
                belowRight.blackBelow == 1 &&
                belowRight.blackRight == 1;
    }

    public static void main(String[] args) {
        int[][] M = {
                {0,0,1,0,0,0,0,0},
                {0,0,1,0,0,1,0,0},
                {1,1,1,1,1,0,1,0},
                {0,0,1,0,1,1,1,1},
                {0,0,1,1,1,1,0,1},
                {1,0,1,0,1,0,0,1},
                {1,0,0,0,1,1,1,1},
                {0,0,0,0,0,0,0,0},
        };

        MaxBlackSquare mbs = new MaxBlackSquare(M);
        int[] res = mbs.findMaxSquare();
        System.out.println(String.format("Upper-left Corner: (%d, %d)\nSize: %d", res[0], res[1], res[2]));
    }
}
