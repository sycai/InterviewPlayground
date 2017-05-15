package RecursionAndDynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by sycai on 5/14/2017.
 * Write an algorithm to print all ways of arranging N queens on an N*N chess board so that none of them share the
 * same row, column, or diagonal. In this case, "diagonal" means all diagonals, not just the two that bisect the board.
 */
public class NQueens {
    private final int GRID_SIZE;
    // Using an integer array has the benefit that when we modify the queen's position at a given row, we don't need to
    // erase the previous position as what we should do when using an two-dimensional array.
    private int[] columns;
    private ArrayList<int[]> solutions;

    public NQueens(int n) {
        GRID_SIZE = n;
        columns = new int[GRID_SIZE];
        solutions = new ArrayList<>();
        placeQueens(0);
    }

    private void placeQueens(int row) {
        if (row == GRID_SIZE) {
            solutions.add(columns.clone());
        } else {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (canPutAt(row, col)) {
                    columns[row] = col;
                    placeQueens(row + 1);
                }
            }
        }
    }

    private boolean canPutAt(int row, int col) { // Only consider the queens put above this row
        for (int r = 0; r < row; r++) {
            if (columns[r] == col)  return false; // Two queens cannot be vertically aligned ...
            if (Math.abs(columns[r] - col) == row - r)  return false; // ... or diagonally aligned
        }
        return true;
    }

    public Iterable<int[]> getSolutions() {
        return solutions;
    }

    public static void main(String[] args) {
        NQueens nq = new NQueens(5);
        for (int[] s : nq.getSolutions()) {
            System.out.println(Arrays.toString(s));
        }
    }
}
