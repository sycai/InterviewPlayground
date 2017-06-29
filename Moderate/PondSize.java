package Moderate;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by sycai on 6/28/2017.
 * CCI 16.19 You have an integer matrix representing a plot of land, where the value at that location represents the
 * height above sea level. A value of zero indicates water. A pond is a region of water connected vertically,
 * horizontally, or diagonally. The size of the pond is the total number of connected water cells. Write a method to
 * compute the sizes of all ponds in the matrix.
 *
 * EXAMPLE
 * Input
 *   0 2 1 0
 *   0 1 0 1
 *   1 1 0 1
 *   0 1 0 1
 * Output: 2, 4, 1(in any order)
 */
public class PondSize {
    public static List<Integer> findPondSizes(int[][] matrix) {
        List<Integer> sizes = new LinkedList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return sizes;

        int row = matrix.length, col = matrix[0].length;
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0 && !visited[i][j]) {
                    sizes.add(DFS(i, j, matrix, visited));
                }
            }
        }
        return sizes;
    }

    private static int DFS(int i, int j, int[][] matrix, boolean[][] visited) {
        if (matrix[i][j] != 0)  return 0;
        if (visited[i][j])      return 0;

        int size = 1;
        visited[i][j] = true;

        boolean canGoUp     = i > 0,
                canGoDown   = i < matrix.length - 1,
                canGoLeft   = j > 0,
                canGoRight  = j < matrix[0].length - 1;

        if (canGoUp) {
            size += DFS(i-1, j, matrix, visited);
            if (canGoLeft) {
                size += DFS(i, j-1, matrix, visited);
                size += DFS(i-1,j-1, matrix, visited);
            }
            if (canGoRight) {
                size += DFS(i, j+1, matrix, visited);
                size += DFS(i-1,j+1, matrix, visited);
            }
        }
        if (canGoDown) {
            size += DFS(i+1, j, matrix, visited);
            if (canGoLeft) {
                size += DFS(i+1, j-1, matrix, visited);
            }
            if (canGoRight) {
                size += DFS(i+1, j+1, matrix, visited);
            }
        }

        return size;
    }

    public static void main(String[] args) {
        int[][] A = {
                {0,2,1,0},
                {0,1,0,1},
                {1,1,0,1},
                {0,1,0,1}
        };
        System.out.println(findPondSizes(A));
    }

}
